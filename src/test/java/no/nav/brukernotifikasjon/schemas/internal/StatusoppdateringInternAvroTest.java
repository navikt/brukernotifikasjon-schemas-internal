package no.nav.brukernotifikasjon.schemas.internal;

import no.nav.brukernotifikasjon.schemas.internal.domain.StatusGlobal;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatusoppdateringInternAvroTest {

    private int expectedSikkerhetsnivaa = 4;

    @Test
    void skalSetteDefaultverdiForSikkerhetsnivaa() {
        StatusoppdateringIntern statusoppdatering = getStatusoppdateringWithDefaultValues();
        assertThat(statusoppdatering.getSikkerhetsnivaa(), is(expectedSikkerhetsnivaa));
    }

    @Test
    void skalSetteNullSomDefaultverdiForStatusIntern() {
        StatusoppdateringIntern statusoppdatering = getStatusoppdateringWithDefaultValues();
        assertThat(statusoppdatering.getStatusIntern(), is(nullValue()));
    }

    private StatusoppdateringIntern getStatusoppdateringWithDefaultValues() {
        return StatusoppdateringIntern.newBuilder()
                .setTidspunkt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .setLink("https://gyldig.url")
                .setStatusGlobal(StatusGlobal.UNDER_BEHANDLING.toString())
                .setSakstema("FP")
                .build();
    }
}
