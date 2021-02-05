package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.Nokkel;
import no.nav.brukernotifikasjon.schemas.internal.builders.util.ValidationUtil;

public class NokkelBuilder {

    private String systembruker;
    private String eventId;
    private String fodselsnummer;

    public NokkelBuilder withSystembruker(String systembruker) {
        this.systembruker = systembruker;
        return this;
    }

    public NokkelBuilder withEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public BeskjedBuilder withFodselsnummer(String fodselsnummer) {
        this.fodselsnummer = fodselsnummer;
        return this;
    }

    public Nokkel build() {
        return new Nokkel(
                ValidationUtil.validateNonNullFieldMaxLength(systembruker, "systembruker", ValidationUtil.MAX_LENGTH_SYSTEMBRUKER),
                ValidationUtil.validateNonNullFieldMaxLength(eventId, "eventId", ValidationUtil.MAX_LENGTH_EVENTID),
                ValidationUtil.validateFodselsnummer(fodselsnummer)
                );
    }
}
