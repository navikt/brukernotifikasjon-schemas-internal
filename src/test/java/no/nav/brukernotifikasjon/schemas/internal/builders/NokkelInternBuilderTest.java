package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.internal.NokkelIntern;
import no.nav.brukernotifikasjon.schemas.internal.builders.exception.FieldValidationException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NokkelInternBuilderTest {

    private String expectedSystembruker = "enSystemBruker";
    private String expectedEventID = UUID.randomUUID().toString();
    private String expectedFodselsnr = "12345678901";
    private String expectedUlid = "12345";

    @Test
    void skalGodtaEventerMedGyldigeFeltverdier() {
        NokkelInternBuilder builder = getBuilderWithDefaultValues();
        NokkelIntern nokkelIntern = builder.build();

        assertThat(nokkelIntern.getSystembruker(), is(expectedSystembruker));
        assertThat(nokkelIntern.getEventId(), is(expectedEventID));
        assertThat(nokkelIntern.getFodselsnummer(), is(expectedFodselsnr));
        assertThat(nokkelIntern.getUlid(), is(expectedUlid));
    }

    @Test
    void skalIkkeGodtaForLangSystembruker() {
        String tooLongSystembruker = String.join("", Collections.nCopies(101, "n"));
        NokkelInternBuilder builder = getBuilderWithDefaultValues().withSystembruker(tooLongSystembruker);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("systembruker"));
    }

    @Test
    void skalIkkeGodtaManglendeSystembruker() {
        NokkelInternBuilder builder = getBuilderWithDefaultValues().withSystembruker(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("systembruker"));
    }

    @Test
    void skalIkkeGodtaUgyldigFodselsnummer() {
        String tooLongFodselsnummer = String.join("", Collections.nCopies(11, "12"));
        NokkelInternBuilder builder = getBuilderWithDefaultValues().withFodselsnummer(tooLongFodselsnummer);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("fodselsnummer"));
    }

    @Test
    void skalIkkeGodtaManglendeFodselsnummer() {
        NokkelInternBuilder builder = getBuilderWithDefaultValues().withFodselsnummer(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("fodselsnummer"));
    }

    @Test
    void skalIkkeGodtaUgyldigUlid() {
        String tooLongUlid = String.join("", Collections.nCopies(101, "n"));
        NokkelInternBuilder builder = getBuilderWithDefaultValues().withUlid(tooLongUlid);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("ulid"));
    }

    @Test
    void skalIkkeGodtaManglendeUlid() {
        NokkelInternBuilder builder = getBuilderWithDefaultValues().withUlid(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("ulid"));
    }

    private NokkelInternBuilder getBuilderWithDefaultValues() {
        return new NokkelInternBuilder()
                .withSystembruker(expectedSystembruker)
                .withEventId(expectedEventID)
                .withFodselsnummer(expectedFodselsnr)
                .withUlid(expectedUlid);
    }

}
