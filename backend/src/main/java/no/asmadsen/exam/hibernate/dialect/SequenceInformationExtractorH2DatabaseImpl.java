package no.asmadsen.exam.hibernate.dialect;

import org.hibernate.boot.model.relational.QualifiedSequenceName;
import org.hibernate.engine.jdbc.env.spi.IdentifierHelper;
import org.hibernate.tool.schema.extract.internal.SequenceInformationImpl;
import org.hibernate.tool.schema.extract.spi.ExtractionContext;
import org.hibernate.tool.schema.extract.spi.SequenceInformation;
import org.hibernate.tool.schema.extract.spi.SequenceInformationExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SequenceInformationExtractorH2DatabaseImpl extends org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorH2DatabaseImpl {
    /**
     * Singleton access
     */
    public static final SequenceInformationExtractorH2DatabaseImpl INSTANCE = new SequenceInformationExtractorH2DatabaseImpl();

    @Override
    public Iterable<org.hibernate.tool.schema.extract.spi.SequenceInformation> extractMetadata(ExtractionContext extractionContext) throws SQLException {
        final IdentifierHelper identifierHelper = extractionContext.getJdbcEnvironment().getIdentifierHelper();
        final Statement statement = extractionContext.getJdbcConnection().createStatement();
        try {
            ResultSet resultSet = statement.executeQuery(
                    "select SEQUENCE_CATALOG, SEQUENCE_SCHEMA, SEQUENCE_NAME, INCREMENT " +
                            "from INFORMATION_SCHEMA.sequences"
            );
            try {
                final List<org.hibernate.tool.schema.extract.spi.SequenceInformation> sequenceInformationList = new ArrayList<SequenceInformation>();
                while ( resultSet.next() ) {
                    sequenceInformationList.add(
                            new SequenceInformationImpl(
                                    new QualifiedSequenceName(
                                            identifierHelper.toIdentifier(
                                                    resultSet.getString( "SEQUENCE_CATALOG" )
                                            ),
                                            identifierHelper.toIdentifier(
                                                    resultSet.getString( "SEQUENCE_SCHEMA" )
                                            ),
                                            identifierHelper.toIdentifier(
                                                    resultSet.getString( "SEQUENCE_NAME" )
                                            )
                                    ),
                                    resultSet.getInt( "INCREMENT" )
                            )
                    );
                }
                return sequenceInformationList;
            }
            finally {try {resultSet.close();}catch (SQLException ignore) {}}}
        finally { try {statement.close(); } catch (SQLException ignore) { } }
    }
}
