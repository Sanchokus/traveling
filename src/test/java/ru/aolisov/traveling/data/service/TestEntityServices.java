package ru.aolisov.traveling.data.service;

import com.google.common.collect.Lists;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.aolisov.traveling.config.AppConfig;
import ru.aolisov.traveling.data.entity.Article;
import ru.aolisov.traveling.data.entity.Country;
import ru.aolisov.traveling.data.entity.Place;
import ru.aolisov.traveling.data.repository.ArticleRepository;
import ru.aolisov.traveling.data.repository.CountryRepository;
import ru.aolisov.traveling.data.repository.PlaceRepository;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class TestEntityServices {

    private static final double DOUBLE_DELTA = 1e-15;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    CountryService countryService;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    PlaceService placeService;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    ArticleService articleService;


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    CountryRepository countryRepo;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    PlaceRepository placeRepo;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    ArticleRepository articleRepo;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        clearAllEntities();
    }

    @After
    public void finishTest() {
        clearAllEntities();
    }

    //region Country
    @Test
    public void createAndReadCountryCorrect() {
        Assert.assertEquals(countryService.get().size(), 0);

        String name1 = "Бразилия";
        String name2 = "Хорватия";
        countryService.create(new Country(name1));
        countryService.create(new Country(name2));

        List<Country> list = countryService.get();
        Assert.assertEquals(list.size(), 2);
        Assert.assertNotNull(countryService.get(name1));
        Assert.assertNotNull(countryService.get(name2));
        String name3 = name1 + name2;
        exception.expect(NoSuchElementException.class);
        countryService.get(name3);
    }

    @Test
    public void updateCountryCorrect() {
        String name1 = "Бразилия";
        String name2 = "Хорватия";
        countryService.create(new Country(name1));
        countryService.create(new Country(name2));

        String newName1 = "Черногория";

        Country country1 = countryService.get(name1);
        country1.setName(newName1);
        Country savedInstance = countryService.update(country1);

        Assert.assertEquals(savedInstance.getName(), newName1);

        List<Country> list = countryService.get();
        Assert.assertEquals(list.size(), 2);
        Assert.assertNotNull(countryService.get(newName1));
        Assert.assertNotNull(countryService.get(name2));
    }

    @Test
    public void deleteCountryCorrect() throws Exception {
        String name1 = "Бразилия";
        String name2 = "Хорватия";
        countryService.create(new Country(name1));
        countryService.create(new Country(name2));

        countryService.delete(countryService.get(name1));

        List<Country> list = countryService.get();
        Assert.assertEquals(list.size(), 1);
        Assert.assertNotNull(countryService.get(name2));
        exception.expect(NoSuchElementException.class);
        countryService.get(name1);
    }

    @Test
    public void createCountryWithExistedNameError() {
        String name1 = "Бразилия";
        String name2 = "Хорватия";
        countryService.create(new Country(name1));
        countryService.create(new Country(name2));
        exception.expect(DataIntegrityViolationException.class);
        countryService.create(new Country(name1));
    }

    //endregion

    //region Place
    @Test
    public void createAndReadPlaceCorrect() {
        String countryName1 = "Бразилия";
        String countryName2 = "Хорватия";
        countryService.create(new Country(countryName1));
        countryService.create(new Country(countryName2));

        String placeName1 = "Рио";
        double x1 = 1.1;
        double y1 = 2.2;
        String placeName2 = "Загреб";
        double x2 = 11.1;
        double y2 = 12.2;
        placeService.create(new Place(placeName1, countryService.get(countryName1), x1, y1));
        placeService.create(new Place(placeName2, countryService.get(countryName2), x2, y2));

        List<Place> list = placeService.get();
        Assert.assertEquals(list.size(), 2);
        Place place1 = placeService.get(placeName1);
        Place place2 = placeService.get(placeName2);
        Assert.assertNotNull(place1);
        Assert.assertEquals(place1.getName(), placeName1);
        Assert.assertEquals(place1.getX(), x1, DOUBLE_DELTA);
        Assert.assertEquals(place1.getY(), y1, DOUBLE_DELTA);
        Assert.assertEquals(place1.getCountry(), countryService.get(countryName1));

        Assert.assertNotNull(place2);
        Assert.assertEquals(place2.getName(), placeName2);
        Assert.assertEquals(place2.getX(), x2, DOUBLE_DELTA);
        Assert.assertEquals(place2.getY(), y2, DOUBLE_DELTA);
        Assert.assertEquals(place2.getCountry(), countryService.get(countryName2));

        String name3 = placeName1 + placeName2;
        Assert.assertTrue(!placeService.exists(name3));
    }

    @Test
    public void updatePlaceCorrect() {
        String countryName1 = "Бразилия";
        String countryName2 = "Хорватия";
        countryService.create(new Country(countryName1));
        countryService.create(new Country(countryName2));

        String placeName1 = "Рио";
        double x1 = 1.1;
        double y1 = 2.2;
        String placeName2 = "Загреб";
        double x2 = 11.1;
        double y2 = 12.2;
        Place place1 = placeService.create(new Place(placeName1, countryService.get(countryName1), x1, y1));
        Place place2 = placeService.create(new Place(placeName2, countryService.get(countryName2), x2, y2));

        String newCountryName1 = "Черногория";
        countryService.create(new Country(newCountryName1));
        double newX1 = 123;
        place1.setCountry(countryService.get(newCountryName1));
        place1.setX(newX1);
        place1 = placeService.update(place1);

        String newPlaceName2 = "Подгорица";
        double newY2 = 456.1155;
        place2.setName(newPlaceName2);
        place2.setY(newY2);
        place2 = placeService.update(place2);

        Assert.assertEquals(place1, placeService.get(place1.getName()));
        Assert.assertEquals(place2, placeService.get(place2.getName()));
        Assert.assertNotEquals(place1, place2);

        List<Place> list = placeService.get();
        Assert.assertEquals(list.size(), 2);

        Assert.assertEquals(place1.getName(), placeName1);
        Assert.assertEquals(place1.getX(), newX1, DOUBLE_DELTA);
        Assert.assertEquals(place1.getY(), y1, DOUBLE_DELTA);
        Assert.assertEquals(place1.getCountry(), countryService.get(newCountryName1));

        Assert.assertEquals(place2.getName(), newPlaceName2);
        Assert.assertEquals(place2.getX(), x2, DOUBLE_DELTA);
        Assert.assertEquals(place2.getY(), newY2, DOUBLE_DELTA);
        Assert.assertEquals(place2.getCountry(), countryService.get(countryName2));
    }

    @Test
    public void deletePlaceCorrect() {
        String countryName1 = "Бразилия";
        String countryName2 = "Хорватия";
        countryService.create(new Country(countryName1));
        countryService.create(new Country(countryName2));

        String placeName1 = "Рио";
        double x1 = 1.1;
        double y1 = 2.2;
        String placeName2 = "Загреб";
        double x2 = 11.1;
        double y2 = 12.2;
        placeService.create(new Place(placeName1, countryService.get(countryName1), x1, y1));
        placeService.create(new Place(placeName2, countryService.get(countryName2), x2, y2));

        placeService.delete(placeService.get(placeName2));

        List<Place> list = placeService.get();
        Assert.assertEquals(list.size(), 1);
        Place place1 = placeService.get(placeName1);
        Assert.assertEquals(place1.getName(), placeName1);
        Assert.assertEquals(place1.getX(), x1, DOUBLE_DELTA);
        Assert.assertEquals(place1.getY(), y1, DOUBLE_DELTA);
        Assert.assertEquals(place1.getCountry(), countryService.get(countryName1));
    }

    @Test
    public void createPlaceWithExistedNameError() {
        String countryName1 = "Бразилия";
        String countryName2 = "Хорватия";
        countryService.create(new Country(countryName1));
        countryService.create(new Country(countryName2));

        String placeName = "Рио";
        double x1 = 1.1;
        double y1 = 2.2;
        double x2 = 11.1;
        double y2 = 12.2;
        placeService.create(new Place(placeName, countryService.get(countryName1), x1, y1));
        exception.expect(DataIntegrityViolationException.class);
        placeService.create(new Place(placeName, countryService.get(countryName2), x2, y2));
    }

    //endregion

    //region Article
    private void setupForArticleTest() {
        Country country1 = countryService.create(new Country("Бразилия"));
        Country country2 = countryService.create(new Country("Хорватия"));
        Country country3 = countryService.create(new Country("Черногория"));
        Place place1 = placeService.create(new Place("Рио", country1, 1, 2));
        Place place2 = placeService.create(new Place("Загреб", country2 , 3, 4));
        Place place3 = placeService.create(new Place("Подгорица",country3 ,5 , 6));

        articleService.create(new Article(
                "Потанцевали в Рио",
                "http://brazil.com",
                2014,
                "some-cool-jesus-photo",
                Lists.newArrayList(place1),
                Lists.newArrayList("Бразилия", "Жара", "Рио", "Фавелы")
        ));
        articleService.create(new Article(
                "Погуляли в Загребе",
                "http://croatia.com",
                2015,
                "some-cool-balkan-photo",
                Lists.newArrayList(place2),
                Lists.newArrayList("Загреб", "Европа", "Тепло")
        ));
        articleService.create(new Article(
                "Покупались в Подгорице",
                "http://montenegro.com",
                2015,
                "some-podgorica-photo",
                Lists.newArrayList(place3),
                Lists.newArrayList("Подгорица", "Ченогория")
        ));
        articleService.create(new Article(
                "Поездили кое-где",
                "http://someplaces.com",
                2016,
                "croatia-and-montenegro-photo",
                Lists.newArrayList(place2, place3),
                Lists.newArrayList("Европа", "Евротур", "Хорватия", "Черногория")
        ));

    }

    @Test
    public void createAndReadArticleCorrect() {
        setupForArticleTest();
        int initSize = articleService.get().size();

        String name = "Россия-матушка";
        String url = "http://spiritual-buckles.ru";
        int year = 2012;
        String photo = "http://900igr.net/datas/okruzhajuschij-mir/Rossija-rodina-moja/0003-003-Matushka-Rossija.jpg";
        List<Place> places = Lists.newArrayList(
                placeService.create(new Place("Владимир", countryService.create(new Country("Россия")), 1, 2)),
                placeService.create(new Place("Ярославль", countryService.get("Россия"), 3, 4))
        );
        List<String> tags = Lists.newArrayList("Русь", "Матушка", "Душа");

        articleService.create(new Article(name, url, year, photo, places, tags));

        Assert.assertEquals(articleService.get().size(), initSize + 1);

        Article article = articleService.get(name);
        Assert.assertEquals(article.getName(), name);
        Assert.assertEquals(article.getUrl(), url);
        Assert.assertEquals(article.getYear(), year);
        Assert.assertEquals(article.getPhoto(), photo);
        assertLinearCollectionsEquals(article.getPlaces(), places);
        assertLinearCollectionsEquals(article.getTags(), tags);
    }

    @Test
    public void updateArticleCorrect() {
        setupForArticleTest();

        List<Article> allArticles = articleService.get();

        //We will change first article in collection

        Article articleToUpdate = allArticles.get(0);
        articleToUpdate.setName(articleToUpdate.getName() + 1);
        articleToUpdate.setPhoto(articleToUpdate.getPhoto() + 1);
        articleToUpdate.setUrl(articleToUpdate.getUrl() + 1);
        articleToUpdate.setYear(articleToUpdate.getYear() + 1);
        articleToUpdate.setTags(Lists.newArrayList("Любые", "Новые", "Теги"));
        articleToUpdate.setPlaces(Lists.newArrayList(placeService.get()));

        Article updatedArticle = articleService.update(articleToUpdate);

        Assert.assertEquals(articleToUpdate, updatedArticle);
        assertArticlesEquals(allArticles, articleService.get());
    }

    @Test
    public void deleteArticleCorrect() {
        setupForArticleTest();
        int initSize = articleService.get().size();
        int countriesCount = countryService.get().size();
        int placesCount = placeService.get().size();

        Article articleToDelete = articleService.get().get(0);
        articleService.delete(articleToDelete);
        Assert.assertEquals(articleService.get().size(), initSize - 1);
        Assert.assertEquals(countryService.get().size(), countriesCount);
        Assert.assertEquals(placeService.get().size(), placesCount);
        Assert.assertTrue(!articleService.exists(articleToDelete.getId()));
        Assert.assertTrue(!articleService.exists(articleToDelete.getName()));
    }

    @Test
    public void createArticleWithExistedNameError() {
        setupForArticleTest();
        String name = "Невероятное приключение";
        articleService.create(new Article(
                name, "http://wooooow.br", 1991, "http://woohooo.ru",
                Lists.newArrayList(placeService.get()),
                Lists.newArrayList("1","2","3")));
        exception.expect(DataIntegrityViolationException.class);
        articleService.create(new Article(
                name, "http://wooooow1.br", 1992, "http://woohooo1.ru",
                Lists.newArrayList(placeService.get()),
                Lists.newArrayList("4","5","6")));
    }
    //endregion

    //region composition

    @Test
    public void deleteCountryOfPlaceError() {
        String countryName1 = "Бразилия";
        countryService.create(new Country(countryName1));
        placeService.create(new Place("Рио", countryService.get(countryName1), 12, 2));

        exception.expect(DataIntegrityViolationException.class);
        countryService.delete(countryService.get(countryName1));
    }

    @Test
    public void deletePlaceFromArticleError() {
        setupForArticleTest();
        String someCountryName = articleService.get().get(0).getPlaces().get(0).getName();

        exception.expect(DataIntegrityViolationException.class);
        placeService.delete(placeService.get(someCountryName));
    }

    //endregion

    private void clearAllEntities() {
        articleRepo.deleteAll();
        placeRepo.deleteAll();
        countryRepo.deleteAll();
    }

    // This is for collections, where we don't have to deal with embedded lists in collection elements.
    private void assertLinearCollectionsEquals(final Collection<?> first, final Collection<?> second) {
        List<?> firstList = Lists.newArrayList(first);
        List<?> secondList = Lists.newArrayList(second);

        Comparator<Object> comparator = (o1, o2) -> o1.hashCode() - o2.hashCode();
        Collections.sort(firstList, comparator);
        Collections.sort(secondList, comparator);
        Assert.assertEquals(firstList, secondList);
    }

    private void assertArticlesEquals(final Collection<Article> first, final Collection<Article> second) {
        List<Article> firstList = Lists.newArrayList(first);
        List<Article> secondList = Lists.newArrayList(second);

        Comparator<Object> linearComparator = (o1, o2) -> o1.hashCode() - o2.hashCode();
        for(Article article: firstList) {
            Collections.sort(article.getPlaces(), linearComparator);
            Collections.sort(article.getTags(), linearComparator);
        }
        for(Article article: secondList) {
            Collections.sort(article.getPlaces(), linearComparator);
            Collections.sort(article.getTags(), linearComparator);
        }
        Comparator<Article> comparator = (o1, o2) -> (int)(o1.getId() - o2.getId());
        Collections.sort(firstList, comparator);
        Collections.sort(secondList, comparator);
        for(int i=0; i<firstList.size(); i++) {
            Article a1 = firstList.get(i);
            Article a2 = secondList.get(i);
            Assert.assertEquals(a1.getId(), a2.getId());
            Assert.assertEquals(a1.getName(), a2.getName());
            Assert.assertEquals(a1.getYear(), a2.getYear());
            Assert.assertEquals(a1.getPhoto(), a2.getPhoto());
            Assert.assertEquals(a1.getUrl(), a2.getUrl());
            assertLinearCollectionsEquals(a1.getPlaces(), a2.getPlaces());
            assertLinearCollectionsEquals(a1.getTags(), a2.getTags());
        }
    }
}
