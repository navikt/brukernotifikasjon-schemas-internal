package no.nav.brukernotifikasjon.schemas.internal.builders;

import no.nav.brukernotifikasjon.schemas.Done;
import no.nav.brukernotifikasjon.schemas.internal.builders.util.ValidationUtil;

import java.time.LocalDateTime;

public class DoneInternalBuilder {

    private LocalDateTime tidspunkt;
    private String grupperingsId;

    public DoneInternalBuilder withTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
        return this;
    }

    public DoneInternalBuilder withGrupperingsId(String grupperingsId) {
        this.grupperingsId = grupperingsId;
        return this;
    }

    public Done build() {
        return new DoneInternal(
                ValidationUtil.localDateTimeToUtcTimestamp(tidspunkt, "tidspunkt", ValidationUtil.IS_REQUIRED_TIDSPUNKT),
                ValidationUtil.validateNonNullFieldMaxLength(grupperingsId, "grupperingsId", ValidationUtil.MAX_LENGTH_GRUPPERINGSID)
        );
    }
}
