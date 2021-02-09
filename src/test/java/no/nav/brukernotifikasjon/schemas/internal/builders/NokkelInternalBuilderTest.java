package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.Nokkel;
import no.nav.brukernotifikasjon.schemas.internal.builders.exception.FieldValidationException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NokkelInternalBuilderTest {

    private String expectedSystembruker = "enSystemBruker";
    private String expectedEventID = UUID.randomUUID().toString();
    private String expectedFodselsnr = "12345678901";
    private String expectedUlid = "12345";

    @Test
    void skalGodtaEventerMedGyldigeFeltverdier() {
        NokkelInternalBuilder builder = getBuilderWithDefaultValues();
        NokkelInternal nokkelInternal = builder.build();

        assertThat(nokkelInternal.getSystembruker(), is(expectedSystembruker));
        assertThat(nokkelInternal.getEventId(), is(expectedEventID));
        assertThat(nokkelInternal.getFodselsnummer(), is(expectedFodselsnr));
        assertThat(nokkelInternal.getUlid(), is(expectedUlid));
    }

    @Test
    void skalIkkeGodtaForLangSystembruker() {
        String tooLongSystembruker = String.join("", Collections.nCopies(101, "n"));
        NokkelInternalBuilder builder = getBuilderWithDefaultValues().withSystembruker(tooLongSystembruker);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("systembruker"));
    }

    @Test
    void skalIkkeGodtaManglendeSystembruker() {
        NokkelInternalBuilder builder = getBuilderWithDefaultValues().withSystembruker(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("systembruker"));
    }

    @Test
    void skalIkkeGodtaUgyldigFodselsnummer() {
        String tooLongFodselsnummer = String.join("", Collections.nCopies(11, "12"));
        NokkelInternalBuilder builder = getBuilderWithDefaultValues().withFodselsnummer(tooLongFodselsnummer);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("fodselsnummer"));
    }

    @Test
    void skalIkkeGodtaManglendeFodselsnummer() {
        NokkelInternalBuilder builder = getBuilderWithDefaultValues().withFodselsnummer(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("fodselsnummer"));
    }

    @Test
    void skalIkkeGodtaUgyldigUlid() {
        String tooLongUlid = String.join("", Collections.nCopies(101, "n"));
        NokkelInternalBuilder builder = getBuilderWithDefaultValues().withUlid(tooLongUlid);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("ulid"));
    }

    @Test
    void skalIkkeGodtaManglendeUlid() {
        NokkelInternalBuilder builder = getBuilderWithDefaultValues().withUlid(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("ulid"));
    }

    private NokkelInternalBuilder getBuilderWithDefaultValues() {
        return new NokkelInternalBuilder()
                .withSystembruker(expectedSystembruker)
                .withEventId(expectedEventID)
                .withFodselsnummer(expectedFodselsnr)
                .withUlid(expectedUlid);
    }

}
