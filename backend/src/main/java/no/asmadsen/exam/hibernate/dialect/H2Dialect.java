package no.asmadsen.exam.hibernate.dialect;


import org.hibernate.tool.schema.extract.spi.SequenceInformationExtractor;

public class H2Dialect extends org.hibernate.dialect.H2Dialect {
    private final SequenceInformationExtractor sequenceInformationExtractor;

    public H2Dialect() {
        super();

        sequenceInformationExtractor = SequenceInformationExtractorH2DatabaseImpl.INSTANCE;
    }

    @Override
    public SequenceInformationExtractor getSequenceInformationExtractor() {
        return sequenceInformationExtractor;
    }
}
