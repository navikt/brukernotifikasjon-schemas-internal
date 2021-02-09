package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.internal.NokkelIntern;
import no.nav.brukernotifikasjon.schemas.internal.builders.util.ValidationUtil;

public class NokkelInternBuilder {

    private String systembruker;
    private String eventId;
    private String fodselsnummer;
    private String ulid;

    public NokkelInternBuilder withSystembruker(String systembruker) {
        this.systembruker = systembruker;
        return this;
    }

    public NokkelInternBuilder withEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public NokkelInternBuilder withFodselsnummer(String fodselsnummer) {
        this.fodselsnummer = fodselsnummer;
        return this;
    }

    public NokkelInternBuilder withUlid(String ulid) {
        this.ulid = ulid;
        return this;
    }

    public NokkelIntern build() {
        return new NokkelIntern(
                ValidationUtil.validateNonNullFieldMaxLength(systembruker, "systembruker", ValidationUtil.MAX_LENGTH_SYSTEMBRUKER),
                ValidationUtil.validateNonNullFieldMaxLength(eventId, "eventId", ValidationUtil.MAX_LENGTH_EVENTID),
                ValidationUtil.validateFodselsnummer(fodselsnummer),
                ValidationUtil.validateNonNullFieldMaxLength(ulid, "ulid", ValidationUtil.MAX_LENGTH_ULID)
                );
    }
}
