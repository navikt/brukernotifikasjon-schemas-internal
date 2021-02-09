package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.internal.OppgaveIntern;
import no.nav.brukernotifikasjon.schemas.internal.builders.domain.Eventtype;
import no.nav.brukernotifikasjon.schemas.internal.builders.util.ValidationUtil;

import java.net.URL;
import java.time.LocalDateTime;

public class OppgaveInternBuilder {

    private LocalDateTime tidspunkt;
    private String grupperingsId;
    private String tekst;
    private URL link;
    private Integer sikkerhetsnivaa;
    private Boolean eksternVarsling = false;

    public OppgaveInternBuilder withTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
        return this;
    }

    public OppgaveInternBuilder withGrupperingsId(String grupperingsId) {
        this.grupperingsId = grupperingsId;
        return this;
    }

    public OppgaveInternBuilder withTekst(String tekst) {
        this.tekst = tekst;
        return this;
    }

    public OppgaveInternBuilder withLink(URL link) {
        this.link = link;
        return this;
    }

    public OppgaveInternBuilder withSikkerhetsnivaa(Integer sikkerhetsnivaa) {
        this.sikkerhetsnivaa = sikkerhetsnivaa;
        return this;
    }

    public OppgaveInternBuilder withEksternVarsling(Boolean eksternVarsling) {
        this.eksternVarsling = eksternVarsling;
        return this;
    }

    public OppgaveIntern build() {
        return new OppgaveIntern(
                ValidationUtil.localDateTimeToUtcTimestamp(tidspunkt, "tidspunkt", ValidationUtil.IS_REQUIRED_TIDSPUNKT),
                ValidationUtil.validateNonNullFieldMaxLength(grupperingsId, "grupperingsId", ValidationUtil.MAX_LENGTH_GRUPPERINGSID),
                ValidationUtil.validateNonNullFieldMaxLength(tekst, "tekst", ValidationUtil.MAX_LENGTH_TEXT_OPPGAVE),
                ValidationUtil.validateLinkAndConvertToString(link, "link", ValidationUtil.MAX_LENGTH_LINK, ValidationUtil.isLinkRequired(Eventtype.OPPGAVE)),
                ValidationUtil.validateSikkerhetsnivaa(sikkerhetsnivaa),
                eksternVarsling
        );
    }
}
