package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.Nokkel;
import no.nav.brukernotifikasjon.schemas.internal.builders.util.ValidationUtil;

public class NokkelInternalBuilder {

    private String systembruker;
    private String eventId;
    private String fodselsnummer;
    private String ulid;

    public NokkelInternalBuilder withSystembruker(String systembruker) {
        this.systembruker = systembruker;
        return this;
    }

    public NokkelInternalBuilder withEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public NokkelInternalBuilder withFodselsnummer(String fodselsnummer) {
        this.fodselsnummer = fodselsnummer;
        return this;
    }

    public NokkelInternalBuilder withUlid(String ulid) {
        this.ulid;
        return this;
    }

    public Nokkel build() {
        return new NokkelInternal(
                ValidationUtil.validateNonNullFieldMaxLength(systembruker, "systembruker", ValidationUtil.MAX_LENGTH_SYSTEMBRUKER),
                ValidationUtil.validateNonNullFieldMaxLength(eventId, "eventId", ValidationUtil.MAX_LENGTH_EVENTID),
                ValidationUtil.validateFodselsnummer(fodselsnummer),
                ValidationUtil.validateNonNullFieldMaxLength(ulid, "ulid", ValidationUtil.MAX_LENGTH_ULID)
                );
    }
}
