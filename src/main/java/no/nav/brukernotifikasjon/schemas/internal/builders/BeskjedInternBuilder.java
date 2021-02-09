package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.internal.BeskjedIntern;
import no.nav.brukernotifikasjon.schemas.internal.builders.domain.Eventtype;
import no.nav.brukernotifikasjon.schemas.internal.builders.util.ValidationUtil;

import java.net.URL;
import java.time.LocalDateTime;

public class BeskjedInternBuilder {

    private LocalDateTime tidspunkt;
    private LocalDateTime synligFremTil;
    private String grupperingsId;
    private String tekst;
    private URL link;
    private Integer sikkerhetsnivaa;
    private Boolean eksternVarsling = false;

    public BeskjedInternBuilder withTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
        return this;
    }

    public BeskjedInternBuilder withSynligFremTil(LocalDateTime synligFremTil) {
        this.synligFremTil = synligFremTil;
        return this;
    }

    public BeskjedInternBuilder withGrupperingsId(String grupperingsId) {
        this.grupperingsId = grupperingsId;
        return this;
    }

    public BeskjedInternBuilder withTekst(String tekst) {
        this.tekst = tekst;
        return this;
    }

    public BeskjedInternBuilder withLink(URL link) {
        this.link = link;
        return this;
    }

    public BeskjedInternBuilder withSikkerhetsnivaa(Integer sikkerhetsnivaa) {
        this.sikkerhetsnivaa = sikkerhetsnivaa;
        return this;
    }

    public BeskjedInternBuilder withEksternVarsling(Boolean eksternVarsling) {
        this.eksternVarsling = eksternVarsling;
        return this;
    }

    public BeskjedIntern build() {
        return new BeskjedIntern(
                ValidationUtil.localDateTimeToUtcTimestamp(tidspunkt, "tidspunkt", ValidationUtil.IS_REQUIRED_TIDSPUNKT),
                ValidationUtil.localDateTimeToUtcTimestamp(synligFremTil, "synligFremTil", ValidationUtil.IS_REQUIRED_SYNLIGFREMTIL),
                ValidationUtil.validateNonNullFieldMaxLength(grupperingsId, "grupperingsId", ValidationUtil.MAX_LENGTH_GRUPPERINGSID),
                ValidationUtil.validateNonNullFieldMaxLength(tekst, "tekst", ValidationUtil.MAX_LENGTH_TEXT_BESKJED),
                ValidationUtil.validateLinkAndConvertToString(link, "link", ValidationUtil.MAX_LENGTH_LINK, ValidationUtil.isLinkRequired(Eventtype.BESKJED)),
                ValidationUtil.validateSikkerhetsnivaa(sikkerhetsnivaa),
                eksternVarsling
        );
    }
}
