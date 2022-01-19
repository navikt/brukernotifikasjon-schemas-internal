package no.nav.brukernotifikasjon.schemas.internal;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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

    @Test
    void SkalSetteDefaultverdiForSynnligFremTil() {
        OppgaveIntern oppgave = getOppgaveWithDefaultValues();
        assertThat(oppgave.getSynligFremTil(), is(nullValue()));
    }

    @Test
    void skalSetteNullSomDefaultverdiEpostVarslingstekst() {
        OppgaveIntern oppgave = getOppgaveWithDefaultValues();
        assertThat(oppgave.getEpostVarslingstekst(), is(nullValue()));
    }

    @Test
    void skalSetteNullSomDefaultverdiEpostVarslingstittel() {
        OppgaveIntern oppgave = getOppgaveWithDefaultValues();
        assertThat(oppgave.getEpostVarslingstittel(), is(nullValue()));
    }

    @Test
    void skalSetteNullSomDefaultverdiSmsVarslingstekst() {
        OppgaveIntern oppgave = getOppgaveWithDefaultValues();
        assertThat(oppgave.getSmsVarslingstekst(), is(nullValue()));
    }

    private OppgaveIntern getOppgaveWithDefaultValues() {
        return OppgaveIntern.newBuilder()
                .setTidspunkt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .setTekst("Du må sende nytt meldekort")
                .setLink("https://gyldig.url")
                .build();
    }
}
