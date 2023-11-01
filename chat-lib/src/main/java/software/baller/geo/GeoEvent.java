package software.baller.geo;

import java.math.BigDecimal;

public record GeoEvent(String eventMessage, BigDecimal latitude, BigDecimal longitude) {}