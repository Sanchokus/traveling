+function($) {
    $(document).ready(function() {
        mainMap = new Map('map');
        mainMap.addClickEvent(function(e) {
            if (confirm('Эти координаты?')) {
                var inputX = window.opener.$('[name="axis_x"]');
                var inputY = window.opener.$('[name="axis_y"]');

                inputX.val(e.latlng.lat);
                inputY.val(e.latlng.lng);
                inputX.trigger('input');
                inputY.trigger('input');
                window.close();
            }
        });
    });
}(jQuery);