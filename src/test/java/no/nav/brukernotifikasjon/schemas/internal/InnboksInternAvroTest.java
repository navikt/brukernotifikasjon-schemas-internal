package no.nav.brukernotifikasjon.schemas.internal;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class InnboksInternAvroTest {

    private int expectedSikkerhetsnivaa = 4;
    private boolean expectedEksternVarsling = false;
    private List<String> expectedPrefererteKanaler = emptyList();

    @Test
    void skalSetteDefaultverdiForSikkerhetsnivaa() {
        InnboksIntern innboks = getInnboksWithDefaultValues();
        assertThat(innboks.getSikkerhetsnivaa(), is(expectedSikkerhetsnivaa));
    }

    @Test
    void skalSetteDefaultverdiForEksternVarsling() {
        InnboksIntern innboks = getInnboksWithDefaultValues();
        assertThat(innboks.getEksternVarsling(), is(expectedEksternVarsling));
    }

    @Test
    void skalSetteDefaultVerdiForPrefererteKanaler() {
        InnboksIntern innboks = getInnboksWithDefaultValues();
        assertThat(innboks.getPrefererteKanaler(), is(expectedPrefererteKanaler));
    }

    @Test
    void skalSetteNullSomDefaultverdiEpostVarslingstekst() {
        InnboksIntern innboks = getInnboksWithDefaultValues();
        assertThat(innboks.getEpostVarslingstekst(), is(nullValue()));
    }

    @Test
    void skalSetteNullSomDefaultverdiEpostVarslingstittel() {
        InnboksIntern innboks = getInnboksWithDefaultValues();
        assertThat(innboks.getEpostVarslingstittel(), is(nullValue()));
    }

    @Test
    void skalSetteNullSomDefaultverdiSmsVarslingstekst() {
        InnboksIntern innboks = getInnboksWithDefaultValues();
        assertThat(innboks.getSmsVarslingstekst(), is(nullValue()));
    }

    private InnboksIntern getInnboksWithDefaultValues() {
        return InnboksIntern.newBuilder()
                .setTidspunkt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .setTekst("Dette er informasjon du må lese")
                .setLink("https://gyldig.url")
                .build();
    }
}
