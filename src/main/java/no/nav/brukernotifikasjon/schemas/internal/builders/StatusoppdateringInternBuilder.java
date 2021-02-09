package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.internal.StatusoppdateringIntern;
import no.nav.brukernotifikasjon.schemas.internal.builders.domain.Eventtype;
import no.nav.brukernotifikasjon.schemas.internal.builders.domain.StatusGlobal;
import no.nav.brukernotifikasjon.schemas.internal.builders.util.ValidationUtil;

import java.net.URL;
import java.time.LocalDateTime;

public class StatusoppdateringInternBuilder {

    private LocalDateTime tidspunkt;
    private String grupperingsId;
    private URL link;
    private Integer sikkerhetsnivaa;
    private StatusGlobal statusGlobal;
    private String statusIntern;
    private String sakstema;

    public StatusoppdateringInternBuilder withTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
        return this;
    }

    public StatusoppdateringInternBuilder withGrupperingsId(String grupperingsId) {
        this.grupperingsId = grupperingsId;
        return this;
    }

    public StatusoppdateringInternBuilder withLink(URL link) {
        this.link = link;
        return this;
    }

    public StatusoppdateringInternBuilder withSikkerhetsnivaa(Integer sikkerhetsnivaa) {
        this.sikkerhetsnivaa = sikkerhetsnivaa;
        return this;
    }

    public StatusoppdateringInternBuilder withStatusGlobal(StatusGlobal statusGlobal) {
        this.statusGlobal = statusGlobal;
        return this;
    }

    public StatusoppdateringInternBuilder withStatusIntern(String statusIntern) {
        this.statusIntern = statusIntern;
        return this;
    }

    public StatusoppdateringInternBuilder withSakstema(String sakstema) {
        this.sakstema = sakstema;
        return this;
    }

    public StatusoppdateringIntern build() {
        return new StatusoppdateringIntern(
                ValidationUtil.localDateTimeToUtcTimestamp(tidspunkt, "tidspunkt", ValidationUtil.IS_REQUIRED_TIDSPUNKT),
                ValidationUtil.validateNonNullFieldMaxLength(grupperingsId, "grupperingsId", ValidationUtil.MAX_LENGTH_GRUPPERINGSID),
                ValidationUtil.validateLinkAndConvertToString(link, "link", ValidationUtil.MAX_LENGTH_LINK, ValidationUtil.isLinkRequired(Eventtype.STATUSOPPDATERING)),
                ValidationUtil.validateSikkerhetsnivaa(sikkerhetsnivaa),
                ValidationUtil.validateStatusGlobal(statusGlobal),
                ValidationUtil.validateNonNullFieldMaxLength(statusIntern, "statusIntern", ValidationUtil.MAX_LENGTH_STATUSINTERN),
                ValidationUtil.validateNonNullFieldMaxLength(sakstema, "sakstema", ValidationUtil.MAX_LENGTH_SAKSTEMA)
        );
    }
}
