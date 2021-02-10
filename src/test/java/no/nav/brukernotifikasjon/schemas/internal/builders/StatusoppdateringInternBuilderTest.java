package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.internal.StatusoppdateringIntern;
import no.nav.brukernotifikasjon.schemas.internal.builders.domain.StatusGlobal;
import no.nav.brukernotifikasjon.schemas.internal.builders.exception.FieldValidationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatusoppdateringInternBuilderTest {

    private String expectedUlid;
    private String expectedGrupperingsId;
    private int expectedSikkerhetsnivaa;
    private URL expectedLink;
    private StatusGlobal expectedStatusGlobal;
    private String expectedStausIntern;
    private String expectedSakstema;
    private LocalDateTime expectedTidspunkt;

    @BeforeAll
    void setUp() throws MalformedURLException {
        expectedUlid = "1x2x3x4x5";
        expectedGrupperingsId = "3456789123456";
        expectedSikkerhetsnivaa = 4;
        expectedLink = new URL("https://gyldig.url");
        expectedStatusGlobal = StatusGlobal.UNDER_BEHANDLING;
        expectedStausIntern = "Vi behandler saken din";
        expectedSakstema = "FP";
        expectedTidspunkt = LocalDateTime.now();
    }

    @Test
    void skalGodtaEventerMedGyldigeFeltverdier() {
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues();
        StatusoppdateringIntern statusoppdateringIntern = builder.build();

        assertThat(statusoppdateringIntern.getGrupperingsId(), is(expectedGrupperingsId));
        assertThat(statusoppdateringIntern.getSikkerhetsnivaa(), is(expectedSikkerhetsnivaa));
        assertThat(statusoppdateringIntern.getLink(), is(expectedLink.toString()));
        assertThat(statusoppdateringIntern.getStatusGlobal(), is(expectedStatusGlobal.toString()));
        assertThat(statusoppdateringIntern.getStatusIntern(), is(expectedStausIntern));
        assertThat(statusoppdateringIntern.getSakstema(), is(expectedSakstema));
        long expectedTidspunktAsUtcLong = expectedTidspunkt.toInstant(ZoneOffset.UTC).toEpochMilli();
        assertThat(statusoppdateringIntern.getTidspunkt(), is(expectedTidspunktAsUtcLong));
        assertThat(statusoppdateringIntern.getUlid(), is(expectedUlid));
    }

    @Test
    void skalIkkeGodtaUgyldigUlid() {
        String tooLongUlid = String.join("", Collections.nCopies(101, "n"));
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withUlid(tooLongUlid);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("ulid"));
    }

    @Test
    void skalIkkeGodtaManglendeUlid() {
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withUlid(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("ulid"));
    }

    @Test
    void skalIkkeGodtaForLangGrupperingsId() {
        String tooLongGrupperingsId = String.join("", Collections.nCopies(101, "1"));
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withGrupperingsId(tooLongGrupperingsId);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("grupperingsId"));
    }

    @Test
    void skalIkkeGodtaManglendeGrupperingsId() {
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withGrupperingsId(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("grupperingsId"));
    }

    @Test
    void skalIkkeGodtaForLavtSikkerhetsnivaa() {
        int invalidSikkerhetsnivaa = 2;
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withSikkerhetsnivaa(invalidSikkerhetsnivaa);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("Sikkerhetsnivaa"));
    }

    @Test
    void skalIkkeGodtaForLangLink() throws MalformedURLException {
        URL invalidLink = new URL("https://" + String.join("", Collections.nCopies(201, "n")));
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withLink(invalidLink);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("link"));
    }

    @Test
    void skalGodtaManglendeLink() {
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withLink(null);
        assertDoesNotThrow(() -> builder.build());
    }

    @Test
    void skalIkkeGodtaManglendeStatusGlobal() {
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withStatusGlobal(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("statusGlobal"));
    }

    @Test
    void skalIkkeGodtaForLangStatusIntern() {
        String tooLongStatusIntern = String.join("", Collections.nCopies(101, "n"));
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withStatusIntern(tooLongStatusIntern);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("statusIntern"));
    }

    @Test
    void skalIkkeGodtaManglendeStatusIntern() {
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withStatusIntern(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("statusIntern"));
    }

    @Test
    void skalIkkeGodtaForLangSakstema() {
        String tooLongSakstema = String.join("", Collections.nCopies(51, "n"));
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withSakstema(tooLongSakstema);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("sakstema"));
    }

    @Test
    void skalIkkeGodtaManglendeSakstema() {
        String tooLongSakstema = String.join("", Collections.nCopies(51, "n"));
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withSakstema(tooLongSakstema);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("sakstema"));
    }

    @Test
    void skalIkkeGodtaManglendeEventtidspunkt() {
        StatusoppdateringInternBuilder builder = getBuilderWithDefaultValues().withTidspunkt(null);
        FieldValidationException exceptionThrown = assertThrows(FieldValidationException.class, () -> builder.build());
        assertThat(exceptionThrown.getMessage(), containsString("tidspunkt"));
    }

    private StatusoppdateringInternBuilder getBuilderWithDefaultValues() {
        return new StatusoppdateringInternBuilder()
                .withUlid(expectedUlid)
                .withGrupperingsId(expectedGrupperingsId)
                .withSikkerhetsnivaa(expectedSikkerhetsnivaa)
                .withLink(expectedLink)
                .withStatusGlobal(expectedStatusGlobal)
                .withStatusIntern(expectedStausIntern)
                .withSakstema(expectedSakstema)
                .withTidspunkt(expectedTidspunkt);
    }
}
