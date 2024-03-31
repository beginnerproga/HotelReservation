package org.mpei.model;

public enum Amenity {
    WIFI("ВАЙФАЙ"),
    SAUNA("САУНА"),
    FREE_PARKING("БЕСПЛАТНАЯ ПАРКОВКА"),
    FREE_BREAKFAST("БЕСПЛАТНЫЙ ЗАВТРАК"),
    POOL("БАССЕЙН"),
    BEACH("ПЛЯЖ"),
    BAR("БАР"),
    FITNESS("ФИТНЕСС"),
    BIKE_RENTAL("АРЕНДА ВЕЛОСИПЕДОВ"),
    FREE_TOURS("БЕСПЛАТНЫЕ ЭКСКУРСИИ"),
    CAR_RENTAL("АРЕНДА АВТО"),

    LIMOUSINE_SERVICE("ЛИМУЗИН СЕРВИС");
    private final String displayName;

    Amenity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
