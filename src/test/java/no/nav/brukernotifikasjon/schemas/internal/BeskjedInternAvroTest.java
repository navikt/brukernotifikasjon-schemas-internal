package no.nav.brukernotifikasjon.schemas.internal;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BeskjedInternAvroTest {

    private int expectedSikkerhetsnivaa = 4;
    private boolean expectedEksternVarsling = false;

    @Test
    void skalSetteDefaultverdiForSikkerhetsnivaa() {
        BeskjedIntern beskjed = getBeskjedWithDefaultValues();
        assertThat(beskjed.getSikkerhetsnivaa(), is(expectedSikkerhetsnivaa));
    }

    @Test
    void skalSetteDefaultverdiForEksternVarsling() {
        BeskjedIntern beskjed = getBeskjedWithDefaultValues();
        assertThat(beskjed.getEksternVarsling(), is(expectedEksternVarsling));
    }

    @Test
    void skalSetteNullSomDefaultverdiForSynligFremTil() {
        BeskjedIntern beskjed = getBeskjedWithDefaultValues();
        assertThat(beskjed.getSynligFremTil(), is(nullValue()));
    }

    @Test
    void skalSetteTomListeSomDefaultverdiForPrefererteKanaler() {
        BeskjedIntern beskjed = getBeskjedWithDefaultValues();
        assertThat(beskjed.getPrefererteKanaler(), is(emptyList()));
    }

    private BeskjedIntern getBeskjedWithDefaultValues() {
        return BeskjedIntern.newBuilder()
                .setTidspunkt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .setTekst("Dette er informasjon du må lese")
                .setLink("https://gyldig.url")
                .build();
    }
}
