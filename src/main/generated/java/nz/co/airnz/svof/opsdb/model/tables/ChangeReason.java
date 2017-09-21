/*
 * This file is generated by jOOQ.
*/
package nz.co.airnz.svof.opsdb.model.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import nz.co.airnz.svof.opsdb.model.Keys;
import nz.co.airnz.svof.opsdb.model.Ops;
import nz.co.airnz.svof.opsdb.model.tables.records.ChangeReasonRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ChangeReason extends TableImpl<ChangeReasonRecord> {

    private static final long serialVersionUID = 120823843;

    /**
     * The reference instance of <code>ops.change_reason</code>
     */
    public static final ChangeReason CHANGE_REASON = new ChangeReason();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChangeReasonRecord> getRecordType() {
        return ChangeReasonRecord.class;
    }

    /**
     * The column <code>ops.change_reason.leg_no</code>.
     */
    public final TableField<ChangeReasonRecord, Integer> LEG_NO = createField("leg_no", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>ops.change_reason.update_key</code>.
     */
    public final TableField<ChangeReasonRecord, Integer> UPDATE_KEY = createField("update_key", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>ops.change_reason.change_reason_rec_seq</code>. Sequence number, internally generated  by the ODS insert system, for each unique instance of this table. This is to avoid having to use free format text as part of the key. It is intended that an agreed change code will be identified by the business that can be used as part of the natural business key, and for analytics purposes
     */
    public final TableField<ChangeReasonRecord, Integer> CHANGE_REASON_REC_SEQ = createField("change_reason_rec_seq", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "Sequence number, internally generated  by the ODS insert system, for each unique instance of this table. This is to avoid having to use free format text as part of the key. It is intended that an agreed change code will be identified by the business that can be used as part of the natural business key, and for analytics purposes");

    /**
     * The column <code>ops.change_reason.change_reason_text</code>.
     */
    public final TableField<ChangeReasonRecord, String> CHANGE_REASON_TEXT = createField("change_reason_text", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

    /**
     * Create a <code>ops.change_reason</code> table reference
     */
    public ChangeReason() {
        this("change_reason", null);
    }

    /**
     * Create an aliased <code>ops.change_reason</code> table reference
     */
    public ChangeReason(String alias) {
        this(alias, CHANGE_REASON);
    }

    private ChangeReason(String alias, Table<ChangeReasonRecord> aliased) {
        this(alias, aliased, null);
    }

    private ChangeReason(String alias, Table<ChangeReasonRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Ops.OPS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ChangeReasonRecord> getPrimaryKey() {
        return Keys.PK_CHANGE_REASON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ChangeReasonRecord>> getKeys() {
        return Arrays.<UniqueKey<ChangeReasonRecord>>asList(Keys.PK_CHANGE_REASON);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ChangeReasonRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ChangeReasonRecord, ?>>asList(Keys.CHANGE_REASON__FK_OPS_CHANGE_REASON_OPS_LEG);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChangeReason as(String alias) {
        return new ChangeReason(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ChangeReason rename(String name) {
        return new ChangeReason(name, null);
    }
}