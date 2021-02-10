package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.internal.DoneIntern;
import no.nav.brukernotifikasjon.schemas.internal.builders.util.ValidationUtil;

import java.time.LocalDateTime;

public class DoneInternBuilder {

    private LocalDateTime tidspunkt;
    private String grupperingsId;
    private String ulid;

    public DoneInternBuilder withUlid(String ulid) {
        this.ulid = ulid;
        return this;
    }

    public DoneInternBuilder withTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
        return this;
    }

    public DoneInternBuilder withGrupperingsId(String grupperingsId) {
        this.grupperingsId = grupperingsId;
        return this;
    }

    public DoneIntern build() {
        return new DoneIntern(
                ValidationUtil.validateNonNullFieldMaxLength(ulid, "ulid", ValidationUtil.MAX_LENGTH_ULID),
                ValidationUtil.localDateTimeToUtcTimestamp(tidspunkt, "tidspunkt", ValidationUtil.IS_REQUIRED_TIDSPUNKT),
                ValidationUtil.validateNonNullFieldMaxLength(grupperingsId, "grupperingsId", ValidationUtil.MAX_LENGTH_GRUPPERINGSID)
        );
    }
}
