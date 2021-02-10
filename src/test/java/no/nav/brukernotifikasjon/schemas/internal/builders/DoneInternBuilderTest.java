package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.internal.DoneIntern;
import no.nav.brukernotifikasjon.schemas.internal.builders.exception.FieldValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoneInternBuilderTest {

    private String expectedUlid = "1x2x3x4x5";
    private String expectedGrupperingsId = "3456789123456";
    private LocalDateTime expectedTidspunkt = LocalDateTime.now(ZoneId.of("UTC"));

    @Test
    void skalGodtaEventerMedGyldigeFeltverdier() {
        DoneInternBuilder builder = getBuilderWithDefaultValues();
        DoneIntern doneIntern = builder.build();

        assertThat(doneIntern.getGrupperingsId(), is(expectedGrupperingsId));
        long expectedTidspunktAsUtcLong = expectedTidspunkt.toInstant(ZoneOffset.UTC).toEpochMilli();
        assertThat(doneIntern.getTidspunkt(), is(expectedTidspunktAsUtcLong));
        assertThat(doneIntern.getUlid(), is(expectedUlid));
    }

    @Test
    void skalIkkeGodtaUgyldigUlid() {
        String tooLongUlid = String.join("", Collections.nCopies(101, "n"));
        DoneInternBuilder builder = getBuilderWithDefaultValues().withUlid(tooLongUlid);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("ulid"));
    }

    @Test
    void skalIkkeGodtaManglendeUlid() {
        DoneInternBuilder builder = getBuilderWithDefaultValues().withUlid(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("ulid"));
    }

    @Test
    void skalIkkeGodtaForLangGrupperingsId() {
        String tooLongGrupperingsId = String.join("", Collections.nCopies(101, "1"));
        DoneInternBuilder builder = getBuilderWithDefaultValues().withGrupperingsId(tooLongGrupperingsId);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("grupperingsId"));
    }

    @Test
    void skalIkkeGodtaManglendeGrupperingsId() {
        DoneInternBuilder builder = getBuilderWithDefaultValues().withGrupperingsId(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("grupperingsId"));
    }

    @Test
    void skalIkkeGodtaManglendeEventtidspunkt() {
        DoneInternBuilder builder = getBuilderWithDefaultValues().withTidspunkt(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("tidspunkt"));
    }

    private DoneInternBuilder getBuilderWithDefaultValues() {
        return new DoneInternBuilder()
                .withUlid(expectedUlid)
                .withGrupperingsId(expectedGrupperingsId)
                .withTidspunkt(expectedTidspunkt);
    }
}
