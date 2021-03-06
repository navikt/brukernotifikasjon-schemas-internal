package no.nav.brukernotifikasjon.schemas.internal;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OppgaveInternAvroTest {

    private int expectedSikkerhetsnivaa = 4;
    private boolean expectedEksternVarsling = false;

    @Test
    void skalSetteDefaultverdiForSikkerhetsnivaa() {
        OppgaveIntern oppgave = getOppgaveWithDefaultValues();
        assertThat(oppgave.getSikkerhetsnivaa(), is(expectedSikkerhetsnivaa));
    }

    @Test
    void skalSetteDefaultverdiForEksternVarsling() {
        OppgaveIntern oppgave = getOppgaveWithDefaultValues();
        assertThat(oppgave.getEksternVarsling(), is(expectedEksternVarsling));
    }

    @Test
    void skalSetteTomListeSomDefaultverdiForPrefererteKanaler() {
        OppgaveIntern oppgave = getOppgaveWithDefaultValues();
        assertThat(oppgave.getPrefererteKanaler(), is(emptyList()));
    }

    private OppgaveIntern getOppgaveWithDefaultValues() {
        return OppgaveIntern.newBuilder()
                .setUlid("1x2x3x4x5")
                .setTidspunkt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .setGrupperingsId("3456789123456")
                .setTekst("Du må sende nytt meldekort")
                .setLink("https://gyldig.url")
                .build();
    }
}
