// 
// Decompiled by Procyon v0.5.30
// 
package com.misys.portal.report;

import org.w3c.dom.DOMException;
import com.misys.portal.report.candidate.CandidateFactory;
import com.misys.portal.report.candidate.Candidate;
import org.apache.ecs.Element;
import org.apache.ecs.xml.XML;
import com.misys.portal.cash.product.ab.common.UserAccountSet;
import com.misys.portal.common.bulk.field.ObjectData;
import java.util.Date;
import com.misys.portal.product.tf.common.FinancingRequestFile;
import com.misys.portal.openaccount.product.ea.common.ExportOpenAccountFile;
import com.misys.portal.openaccount.product.io.common.ImportOpenAccountFile;
import com.misys.portal.product.lc.common.LetterOfCreditFile;
import com.misys.portal.product.si.common.StandbyIssuedFile;
import com.misys.portal.product.bg.common.BankerGuaranteeFile;
import com.misys.portal.services.holidayscutoff.HolidaysAndCutOff;
import java.util.Calendar;
import com.misys.portal.services.holidayscutoff.CutOffTime;
import com.misys.portal.services.holidayscutoff.BusinessCalendar;
import com.misys.portal.product.bk.util.BulkUtils;
import com.misys.portal.product.ft.util.FundTransferUtils;
import com.misys.portal.cash.product.ft.common.FundTransfer;
import org.apache.turbine.services.TurbineServices;
import com.misys.portal.treasury.services.FT.FTProcessingService;
import com.misys.portal.treasury.utils.TreasuryUtils;
import com.misys.portal.cash.beans.FTDataBean;
import com.misys.portal.treasury.product.ft.common.FundTransferFile;
import com.misys.portal.cash.product.fx.common.ForeignExchangeFile;
import com.misys.portal.product.td.util.TermDepositUtils;
import com.misys.portal.common.tools.Utils;
import com.misys.portal.treasury.services.TreasuryProcessingServiceFactory;
import com.misys.portal.cash.beans.StandingInstructions;
import com.misys.portal.cash.product.td.common.TermDepositFile;
import org.apache.turbine.om.security.User;
import com.misys.portal.product.bk.common.BulkFile;
import com.misys.portal.product.common.CrossReference;
import com.misys.portal.common.resources.DefaultResourceProvider;
import com.misys.portal.entity.common.Entity;
import com.misys.portal.services.rundata.GTPRunData;
import com.misys.portal.common.bulk.field.BulkField;
import com.misys.portal.common.bulk.field.BulkFieldFactory;
import com.misys.portal.product.util.MasterProductFile;
import com.misys.portal.services.security.authentication.GTPAuthentication;
import java.util.Vector;
import com.misys.portal.common.bulk.field.IntegerField;
import com.misys.portal.common.localization.Localization;
import com.misys.portal.security.GTPSecurity;
import com.misys.portal.common.security.GTPSecurityCheck;
import com.misys.portal.product.util.builder.IProductFileBuilder;
import com.misys.portal.product.util.TransactionProductFile;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.tracer.Auditable;
import com.misys.portal.common.bulk.field.StringField;
import java.sql.Timestamp;
import org.apache.commons.lang.StringUtils;
import com.misys.portal.product.util.builder.TransactionProductFileBuilder;
import java.math.BigDecimal;
import com.misys.portal.common.bulk.field.IdField;
import com.misys.portal.services.db.PoolBrokerService;
import com.misys.portal.common.services.Audit;
import com.misys.portal.product.util.generator.BatchIdGenerator;
import com.misys.portal.security.GTPUser;
import com.misys.portal.common.tools.URLListKeys;
import com.misys.portal.report.aggregate.AggregateFactory;
import com.misys.portal.report.tools.DOMTools;
import org.w3c.dom.Node;
import com.misys.portal.report.processor.Processor;
import com.misys.portal.report.processor.ProcessorFactory;
import com.misys.portal.report.processor.InputSourceFactory;
import com.misys.portal.report.viewer.ViewerFactory;
import com.misys.portal.common.tracer.Profiler;
import java.io.OutputStream;
import com.misys.portal.report.field.SimpleField;
import com.misys.portal.report.processor.GlobalAggregateRecord;
import java.util.Iterator;
import com.misys.portal.report.aggregate.Aggregate;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.misys.portal.common.tools.ParameterData;
import com.misys.portal.common.tools.ParameterKey;
import java.util.HashMap;
import java.util.ArrayList;
import com.misys.portal.report.tools.Logger;
import com.misys.portal.report.value.Join;
import com.misys.portal.report.value.Value;
import com.misys.portal.report.value.StringValue;
import com.misys.portal.product.util.builder.ProductFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import org.apache.turbine.Turbine;
import org.apache.commons.logging.LogFactory;
import java.net.URL;
import com.misys.portal.security.GTPCompany;
import org.apache.turbine.util.RunData;
import com.misys.portal.common.validation.GTPBusinessValidationException;
import com.misys.portal.common.security.GTPAuthorisation;
import com.misys.portal.product.util.ProductFile;
import java.util.Map;
import java.util.List;
import com.misys.portal.report.viewer.Viewer;
import java.util.Set;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import com.misys.portal.report.processor.ProcessorNode;

public class ListDef extends ProcessorNode implements ListDefNode {

    private static final Log LOG;
    public static final String ORDER_TYPE_ASCENDING = "a";
    public static final String ORDER_TYPE_DESCENDING = "d";
    public static final String PAGE = "page";
    public static final String BATCH_MODE_SUBMIT = "submit";
    public static final String BATCH_MODE_RESUBMIT = "resubmit";
    public static final String BATCH_MODE_DELETE = "delete";
    public static final String BATCH_MODE_CANCEL = "cancel";
    public static final String BATCH_MODE_PRINT = "print";
    public static final String BATCH_MODE_GROUP = "group";
    public static final String BATCH_MODE_BATCH = "batch_operation";
    public static final String BATCH_MODE_REPRICING = "repricing_operation";
    public static final String BATCH_MODE_INIT = "init";
    public static final String BATCH_MODE_FORWARD = "forward";
    public static final String BATCH_MODE_ZIP = "zip";
    public static final String BATCH_MODE_CANCEL_DISABLED = "cancelDisabled";
    public static final String BATCH_MODE_PROCESS = "process";
    public static final String[] AVAILABLE_PRODUCTS;
    private static final String GTP_LIST_EXPORT_MAXRECORDS = "gtp.list.export.maxrecords";
    private static final Configuration conf;
    private static final String NAME_STRING = "name";
    public static final String HIERARCHY_MONTH_FORMAT = "lists.hierarchy.month.format";
    public static final String HIERARCHY_YEAR_FORMAT = "lists.hierarchy.year.format";
    public static final String SHOW_HINT = "lists.show.hint";
    private static final Set<String> entityNamesSet;
    private static final String[] subProdsForBuilder;
    private boolean isReauthPassed;
    private String reauthFailureMessage;
    private Viewer viewer;
    private String dialogType;
    private String script;
    private boolean showTitle;
    private int searchColumns;
    public String identifier;
    public boolean crossPageSelection;
    private final Group rootGroup;
    public int orderIndex;
    public String orderType;
    public int pageSize;
    public int pageOffset;
    public int pageCount;
    public boolean forceDisplaySearch;
    public int numberOfRows;
    public List<Batch> batches;
    public String listKeys;
    public int nbRecordsDeleted;
    public int nbRecordsGrouped;
    public Map<ProductFile, GTPAuthorisation> acceptedFiles;
    public Map<ProductFile, GTPAuthorisation> rejectedFiles;
    public Map<ProductFile, GTPAuthorisation> autoForwardedFiles;
    public String autoForward;
    public int nbRecordsZipped;
    public Map<ProductFile, GTPAuthorisation> failedFiles;
    public Map<ProductFile, GTPBusinessValidationException> businessValidationRejectFiles;
    public Map initiatedProductRef;
    public String additionalHelp;
    public boolean distinct;
    public boolean allBanks;
    public Map equivalentRates;
    private final List candidates;
    private final List<Node> candidateNodes;
    private final Map parameters;
    private final List orderedParameters;
    private final List filters;
    private final List<Column> columns;
    private final Map columnsByName;
    private boolean grouped;
    private boolean exported;
    private boolean aggregated;
    private final List<Aggregate> aggregates;
    private RunData runData;
    private GTPCompany company;
    boolean ordered;
    String description;
    String message;
    private String queryType;
    private String viewerType;
    public String gridFixedSize;
    boolean hugeMode;
    boolean huge;
    int columnIndex;
    int rawIndex;
    private URL url;
    private InputSource source;
    private String reportId;
    private String templateId;
    public String autoHeight;
    private String selectionMode;
    private String onSelectionChangedScript;
    private String onSelectionClearedScript;
    private String subscribeTopic;
    public String jsBinding;
    private boolean hideSearch;
    public String permission;

    static {
        LOG = LogFactory.getLog((Class) ListDef.class);
        conf = Turbine.getConfiguration();
        entityNamesSet = new HashSet<String>(Arrays.asList("entity", "EntityAccount@entity@abbv_name", "UserAccount@EntityAccount@entity@abbv_name"));
        subProdsForBuilder = new String[]{"TRINT", "TRTPT", "TRTD"};
        AVAILABLE_PRODUCTS = ProductFactory.getAvailableProductsAsArray();
    }

    protected static final void configureBusinessCriteria(final BusinessCriteria criteria, final String type) {
        if (type.equals("isAmendement")) {
            criteria.setField(new Column("tnx_type_code"));
            criteria.setComparison(Criteria.EQUAL);
            criteria.setValue(new StringValue("03"));
        } else if (type.equals("isNew")) {
            criteria.setField(new Column("tnx_type_code"));
            criteria.setComparison(Criteria.EQUAL);
            criteria.setValue(new StringValue("01"));
        } else if (type.equals("isInquire")) {
            criteria.setField(new Column("tnx_type_code"));
            criteria.setComparison(Criteria.EQUAL);
            criteria.setValue(new StringValue("13"));
        } else if (type.equals("isCancel")) {
            criteria.setField(new Column("tnx_type_code"));
            criteria.setComparison(Criteria.EQUAL);
            criteria.setValue(new StringValue("14"));
        } else if (type.equals("isLastTransaction")) {
            criteria.setField(new Column("bo_release_dttm"));
            criteria.setComparison(Criteria.INNER_JOIN);
            final Join join = new Join();
            join.name = "@lastDate";
            criteria.setValue(join);
        } else if (type.equals("hasNoPendingAmendmentTransactions")) {
            criteria.setField(new Column("tnx_type_code"));
            criteria.setComparison(Criteria.HAS_NO_PENDING_TRANSACTIONS);
            criteria.setValue(new StringValue("03"));
        } else if (type.equals("hasNoPendingInquireTransactions")) {
            criteria.setField(new Column("tnx_type_code"));
            criteria.setComparison(Criteria.HAS_NO_PENDING_TRANSACTIONS);
            criteria.setValue(new StringValue("13"));
        } else if (type.equals("hasNoCancelledRecurringTransactions")) {
            criteria.setField(new Column("sub_tnx_type_code"));
            criteria.setComparison(Criteria.HAS_NOT_IN_PROGRESS_TRANSACTIONS);
            criteria.setValue(new StringValue("61"));
        } else {
            Logger.warn("[ListDef.configureBusinessCriteria] Unknown BusinessCriteria type: " + type + ".");
        }
    }

    public ListDef() {
        super(null);
        this.isReauthPassed = true;
        this.showTitle = true;
        this.candidates = new ArrayList();
        this.candidateNodes = new ArrayList();
        this.parameters = new HashMap();
        this.orderedParameters = new ArrayList();
        this.filters = new ArrayList();
        this.columns = new ArrayList();
        this.columnsByName = new HashMap();
        this.aggregates = new ArrayList<Aggregate>();
        this.equivalentRates = new HashMap();
        this.ordered = true;
        this.orderType = "a";
        this.crossPageSelection = false;
        this.rootGroup = new Group(this);
        this.queryType = "sql";
        this.viewerType = "screen";
        this.huge = false;
        this.hugeMode = false;
        this.hideSearch = false;
        this.forceDisplaySearch = false;
        this.columnIndex = 0;
        this.rawIndex = 0;
        this.batches = new ArrayList();
        this.pageSize = 0;
        try {
            final ParameterKey keys = new ParameterKey();
            keys.setCompany_id("**");
            keys.setBrch_code("00001");
            keys.setParm_id("P420");
            final ParameterData parametersData = new ParameterData(keys);
            if (parametersData.exactMatch && parametersData.getData("DATA_1") != null) {
                this.pageSize = Integer.parseInt(parametersData.getData("DATA_1"));
            }
        } catch (Exception e) {
            Logger.error("ListDef, unable to initialize System Parameters", e);
        }
        this.pageCount = 0;
        this.distinct = true;
    }

    public ListDef(final InputSource listDef) {
        this();
        this.source = listDef;
        Logger.note("Reading a new ListDef: " + listDef);
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            final DocumentBuilder parser = factory.newDocumentBuilder();
            final Document doc = parser.parse(this.source.getSource());
            if (doc != null) {
                final NodeList children = doc.getChildNodes();
                for (int i = 0; i < children.getLength(); ++i) {
                    final String name = children.item(i).getNodeName();
                    if ("listdef".equals(name)) {
                        this.fromNode(children.item(i));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            ListDef.LOG.error((Object) e.getMessage(), (Throwable) e);
        }
    }

    @Override
    public Object accept(final Viewer aViewer) {
        return aViewer.visit(this);
    }

    public void addParameter(final String name, final Parameter parameter) {
        this.parameters.put(name, parameter);
    }

    public void addRecord(final Record record) {
        this.rootGroup.addRecord(record);
        for (final Aggregate agg : this.aggregates) {
            agg.accumulate(record);
        }
    }

    public Iterator candidatesIterator() {
        return this.candidates.iterator();
    }

    public Iterator elements() {
        final List aggregateRecords = new ArrayList();
        for (final Aggregate agg : this.aggregates) {
            final SimpleField field = agg.getValue();
            if (field != null) {
                final Record record = new GlobalAggregateRecord();
                record.setName(agg.getName());
                aggregateRecords.add(record);
                record.add(this.getColumn((String) agg.getOperands().get(0)), field);
            }
        }
        return this.rootGroup.elements(aggregateRecords);
    }

    public Object execute(final RunData aRunData) {
        return this.execute(aRunData, null);
    }

    public Object execute(final RunData aRunData, final OutputStream outputStream) {
        this.runData = aRunData;
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "ListDef Engine");
        Object result = null;
        com.misys.portal.report.processor.InputSource src = null;
        try {
            ListDef.LOG.debug((Object) "Update Execution Context...");
            profiler.start((Class) this.getClass(), "updateExecutionContext");
            if (this.viewerType != null && this.viewerType.length() > 0) {
                this.viewer = ViewerFactory.newViewer(this.viewerType);
                if (this.viewer == null) {
                    throw new RuntimeException("Unknown or not set Viewer type: " + this.viewerType);
                }
                this.viewerType = this.viewer.getType();
            }
            this.updateExecutionContext();
            profiler.stop((Class) this.getClass(), "updateExecutionContext");
            Logger.debug("Querying...");
            profiler.start((Class) this.getClass(), "Querying");
            src = InputSourceFactory.create(this.queryType, this, aRunData);
            profiler.stop((Class) this.getClass(), "Querying");
            Logger.debug("Processing...");
            profiler.start((Class) this.getClass(), "Processing");
            final Processor processor = ProcessorFactory.newProcessor(this);
            final ProcessorNode processorResult = processor.process(src);
            profiler.stop((Class) this.getClass(), "Processing");
            src.close();
            src = null;
            Logger.debug("Viewing...");
            result = this.viewer.transform(aRunData, processorResult, this, outputStream);
        } catch (Exception e) {
            ListDef.LOG.error((Object) "List Framework: ", (Throwable) e);
            throw new RuntimeException(e.toString());
        } finally {
            if (src != null) {
                try {
                    src.close();
                } catch (Exception e2) {
                    ListDef.LOG.error((Object) "List Framework:error in closing resource ", (Throwable) e2);
                }
            }
        }
        if (src != null) {
            try {
                src.close();
            } catch (Exception e2) {
                ListDef.LOG.error((Object) "List Framework:error in closing resource ", (Throwable) e2);
            }
        }
        profiler.stop((Class) this.getClass(), "ListDef Engine");
        profiler.print((Class) this.getClass(), "ListDef Engine", "GTP list execution ");
        profiler.release((Class) this.getClass(), "ListDef Engine");
        return result;
    }

    @Override
    public final void fromNode(final Node node) {
        System.out.println("<< fromNode >>");
        final NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("candidate".equals(name)) {
                if (children.item(i) != null) {
                    this.readCandidateNode(children.item(i));
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("filter".equals(name)) {
                if (children.item(i) != null) {
                    this.readFilterNode(children.item(i));
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("parameter".equals(name)) {
                if (children.item(i) != null) {
                    this.readParameterNode(children.item(i));
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {

            System.out.println("<< A >>");
            final String name = children.item(i).getNodeName();
            if ("column".equals(name)) {
                System.out.println("<< B >>");
                if (children.item(i) != null) {
                    System.out.println("<< C >>");
                    this.readColumnNode(children.item(i));
                    System.out.println("<< D >>");
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("aggregate".equals(name)) {
                if (children.item(i) != null) {
                    this.readAggregateNode(children.item(i));
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("group".equals(name)) {
                if (children.item(i) != null) {
                    this.readGroupNode(children.item(i));
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("batch".equals(name)) {
                if (children.item(i) != null) {
                    this.readBatchNode(children.item(i));
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("name".equals(name)) {
                final Node nameNode = children.item(i).getFirstChild();
                if (nameNode != null) {
                    this.setName(nameNode.getNodeValue());
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("script".equals(name)) {
                if (children.item(i) != null) {
                    this.setScript(DOMTools.getNodeValue(children.item(i)).trim());
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("onSelectionChangedScript".equals(name)) {
                if (children.item(i) != null) {
                    this.setOnSelectionChangedScript(DOMTools.getNodeValue(children.item(i)).trim());
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("onSelectionClearedScript".equals(name)) {
                if (children.item(i) != null) {
                    this.setOnSelectionClearedScript(DOMTools.getNodeValue(children.item(i)).trim());
                }
            }
        }
        if (node.getAttributes().getNamedItem("default_order") != null) {
            final String defaultOrder = node.getAttributes().getNamedItem("default_order").getNodeValue();
            String defaultOrderType;
            if (node.getAttributes().getNamedItem("default_order_type") != null) {
                defaultOrderType = ("a".equals(node.getAttributes().getNamedItem("default_order_type").getNodeValue()) ? "a" : "d");
            } else {
                defaultOrderType = "a";
            }
            this.setDefaultSort(defaultOrder, defaultOrderType);
        }
        if (node.getAttributes().getNamedItem("huge") != null) {
            this.hugeMode = "y".equalsIgnoreCase(node.getAttributes().getNamedItem("huge").getNodeValue());
        }
        if (node.getAttributes().getNamedItem("forceDisplaySearch") != null) {
            this.forceDisplaySearch = "y".equalsIgnoreCase(node.getAttributes().getNamedItem("forceDisplaySearch").getNodeValue());
        }
        if (node.getAttributes().getNamedItem("page") != null) {
            this.pageSize = Integer.parseInt(node.getAttributes().getNamedItem("page").getNodeValue().trim());
        }
        if (node.getAttributes().getNamedItem("hideSearch") != null) {
            this.hideSearch = "true".equalsIgnoreCase(node.getAttributes().getNamedItem("hideSearch").getNodeValue());
        }
        if (node.getAttributes().getNamedItem("fixed_grid_size") != null) {
            this.gridFixedSize = node.getAttributes().getNamedItem("fixed_grid_size").getNodeValue().trim();
        }
        if (node.getAttributes().getNamedItem("show") != null) {
            this.autoHeight = node.getAttributes().getNamedItem("show").getNodeValue().trim();
        }
        if (node.getAttributes().getNamedItem("help") != null) {
            this.additionalHelp = node.getAttributes().getNamedItem("help").getNodeValue().trim();
        }
        if (node.getAttributes().getNamedItem("distinct") != null) {
            this.distinct = "y".equalsIgnoreCase(node.getAttributes().getNamedItem("distinct").getNodeValue());
        }
        if (node.getAttributes().getNamedItem("ordered") != null) {
            this.ordered = "y".equalsIgnoreCase(node.getAttributes().getNamedItem("ordered").getNodeValue());
        }
        if (node.getAttributes().getNamedItem("source") != null) {
            final String sourceType = node.getAttributes().getNamedItem("source").getNodeValue();
            this.queryType = sourceType;
        }
        if (node.getAttributes().getNamedItem("viewer") != null) {
            this.viewerType = node.getAttributes().getNamedItem("viewer").getNodeValue();
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("all_banks_flag".equals(name)) {
                this.allBanks = "y".equalsIgnoreCase(DOMTools.getNodeValue(children.item(i)).trim());
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("description".equals(name)) {
                if (children.item(i) != null) {
                    this.setDescription(DOMTools.getNodeValue(children.item(i)).trim());
                }
            }
        }
        for (int i = 0; i < children.getLength(); ++i) {
            final String name = children.item(i).getNodeName();
            if ("reportId".equals(name)) {
                if (children.item(i) != null) {
                    this.setReportId(DOMTools.getNodeValue(children.item(i)).trim());
                }
            }
        }
        if (node.getAttributes().getNamedItem("selection_mode") != null) {
            this.selectionMode = node.getAttributes().getNamedItem("selection_mode").getNodeValue().trim();
        }
        if (node.getAttributes().getNamedItem("subscribe_topic") != null) {
            this.subscribeTopic = node.getAttributes().getNamedItem("subscribe_topic").getNodeValue().trim();
        }
        if (node.getAttributes().getNamedItem("js_binding") != null) {
            this.jsBinding = node.getAttributes().getNamedItem("js_binding").getNodeValue().trim();
        }
        if (node.getAttributes().getNamedItem("identifier") != null) {
            this.identifier = node.getAttributes().getNamedItem("identifier").getNodeValue().trim();
        }
        if (node.getAttributes().getNamedItem("crossPageSelection") != null) {
            if ("y".equalsIgnoreCase(node.getAttributes().getNamedItem("crossPageSelection").getNodeValue())) {
                this.crossPageSelection = true;
            } else {
                this.crossPageSelection = false;
            }
        }
        if (node.getAttributes().getNamedItem("dialog_type") != null) {
            this.dialogType = node.getAttributes().getNamedItem("dialog_type").getNodeValue().trim();
        }
        if (node.getAttributes().getNamedItem("search_columns") != null) {
            final String searchColumnsXml = node.getAttributes().getNamedItem("search_columns").getNodeValue();
            if (searchColumnsXml != null) {
                this.searchColumns = Integer.parseInt(searchColumnsXml);
            } else {
                this.searchColumns = 0;
            }
        }
        if (node.getAttributes().getNamedItem("permission") != null) {
            this.permission = node.getAttributes().getNamedItem("permission").getNodeValue().trim();
        }
    }

    public List getAggregates() {
        return this.aggregates;
    }

    public String getAutoForward() {
        return this.autoForward;
    }

    public List<Batch> getBatches() {
        return this.batches;
    }

    public Iterator getCandidateNodes() {
        return this.candidateNodes.iterator();
    }

    public String getChartType() {
        return this.rootGroup.getChartType();
    }

    public final Column getColumn(final String name) {
        final Column result = (Column) this.columnsByName.get(name);
        if (result == null) {
            Logger.error("[ListDef.getColumn(name)] Unknown Column name: " + name);
        }
        return result;
    }

    public final List getColumns() {
        return this.columns;
    }

    public final Iterator getColumnsIterator() {
        return this.columns.iterator();
    }

    public GTPCompany getCompany() {
        return this.company;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDialogType() {
        return this.dialogType;
    }

    public List getFilters() {
        return this.filters;
    }

    public String getListKeys() {
        return this.listKeys;
    }

    public String getMessage() {
        return this.message;
    }

    public String getOnSelectionChangedScript() {
        return this.onSelectionChangedScript;
    }

    public String getOnSelectionClearedScript() {
        return this.onSelectionClearedScript;
    }

    public List getOrderedParameters() {
        return this.orderedParameters;
    }

    public int getOrderIndex() {
        return this.orderIndex;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public Map getParameters() {
        return this.parameters;
    }

    public String getQueryType() {
        return this.queryType;
    }

    protected Map<String, String> getReAuthConfigParams(final String tnxTypeCode) {
        final Map<String, String> reAuthConfigParams = new HashMap<String, String>();
        reAuthConfigParams.put("productCode", "MB");
        reAuthConfigParams.put("transactionTypeCode", tnxTypeCode);
        reAuthConfigParams.put("list_keys", this.listKeys);
        return reAuthConfigParams;
    }

    public String getReauthFailureMessage() {
        return this.reauthFailureMessage;
    }

    public String getReportId() {
        return this.reportId;
    }

    public Group getRootGroup() {
        return this.rootGroup;
    }

    public final RunData getRunData() {
        return this.runData;
    }

    public String getScript() {
        return this.script;
    }

    public int getSearchColumns() {
        return this.searchColumns;
    }

    public String getSelectionMode() {
        return this.selectionMode;
    }

    public final InputSource getSource() {
        return this.source;
    }

    public String getSubscribeTopic() {
        return this.subscribeTopic;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public final URL getUrl() {
        return this.url;
    }

    public Viewer getViewer() {
        return this.viewer;
    }

    public String getViewerType() {
        return this.viewerType;
    }

    public boolean hasBatches() {
        return !this.getBatches().isEmpty();
    }

    public boolean hasBatchesPermission() {
        if (!this.hasBatches()) {
            return false;
        }
        for (final Batch theBatch : this.getBatches()) {
            if (theBatch.hasPermission()) {
                return true;
            }
        }
        return false;
    }

    public boolean isAggregated() {
        return this.aggregated;
    }

    public boolean isCharted() {
        return this.rootGroup.isCharted();
    }

    public final boolean isExported() {
        return this.exported;
    }

    public boolean isForceDisplaySearch() {
        return this.forceDisplaySearch;
    }

    public boolean isGrouped() {
        return this.grouped;
    }

    public boolean isHideSearch() {
        return this.hideSearch;
    }

    public boolean isHuge() {
        return this.huge;
    }

    public boolean isHugeMode() {
        return this.hugeMode;
    }

    public boolean isOrdered() {
        return this.ordered;
    }

    public boolean isReauthPassed() {
        return this.isReauthPassed;
    }

    public boolean isShowTitle() {
        return this.showTitle;
    }

    private void readAggregateNode(final Node node) {
        try {
            final Aggregate aggregate = AggregateFactory.getAggregateFromNode(node, this);
            aggregate.reset();
            this.aggregates.add(aggregate);
            this.aggregated = true;
        } catch (ClassNotFoundException e) {
            ListDef.LOG.error((Object) e.getMessage(), (Throwable) e);
        }
    }

    private void readBatchNode(final Node node) {
        final Batch theBatch = new Batch();
        final Map batchParameters = new HashMap();
        this.acceptedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        this.rejectedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        this.failedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        this.initiatedProductRef = new HashMap();
        if (node.getAttributes().getNamedItem("type") != null) {
            theBatch.setType(node.getAttributes().getNamedItem("type").getNodeValue());
        } else {
            theBatch.setType("");
        }
        if (node.getAttributes().getNamedItem("name") != null) {
            theBatch.setName(node.getAttributes().getNamedItem("name").getNodeValue());
        }
        if (node.getAttributes().getNamedItem("permission") != null) {
            theBatch.setPermission(node.getAttributes().getNamedItem("permission").getNodeValue());
        }
        if (node.getAttributes().getNamedItem("widgetid") != null) {
            theBatch.setWidgetId(node.getAttributes().getNamedItem("widgetid").getNodeValue());
        }
        final NodeList batchChildren = node.getChildNodes();
        for (int j = 0; j < batchChildren.getLength(); ++j) {
            final Node batchNode = batchChildren.item(j);
            if ("parameter".equalsIgnoreCase(batchNode.getNodeName()) && batchNode.getAttributes().getNamedItem("name") != null) {
                final URLParameter par = new URLParameter();
                par.setName(batchNode.getAttributes().getNamedItem("name").getNodeValue());
                if (batchNode.getAttributes().getNamedItem("column") != null) {
                    par.setColumn(batchNode.getAttributes().getNamedItem("column").getNodeValue());
                } else if (batchNode.getAttributes().getNamedItem("value") != null) {
                    par.setValue(batchNode.getAttributes().getNamedItem("value").getNodeValue());
                } else if (batchNode.getAttributes().getNamedItem("binding_code") != null) {
                    par.setBindingCode(batchNode.getAttributes().getNamedItem("binding_code").getNodeValue());
                } else if (batchNode.getAttributes().getNamedItem("business_code") != null) {
                    par.setBusinessCode(batchNode.getAttributes().getNamedItem("business_code").getNodeValue());
                }
                batchParameters.put(batchNode.getAttributes().getNamedItem("name").getNodeValue().toUpperCase(), par);
                theBatch.setParameters(batchParameters);
            } else if ("icon".equalsIgnoreCase(batchNode.getNodeName())) {
                if (batchNode.getAttributes().getNamedItem("loca_key") != null) {
                    theBatch.setLocalizationKey(batchNode.getAttributes().getNamedItem("loca_key").getNodeValue());
                }
                if (batchNode.getAttributes().getNamedItem("image") != null) {
                    theBatch.setIconPath(batchNode.getAttributes().getNamedItem("image").getNodeValue());
                }
                if (batchNode.getAttributes().getNamedItem("permission") != null) {
                    theBatch.setPermission(batchNode.getAttributes().getNamedItem("permission").getNodeValue());
                }
            } else if ("javascript".equalsIgnoreCase(batchNode.getNodeName()) && batchNode.getAttributes().getNamedItem("url") != null) {
                theBatch.setJavascript(batchNode.getAttributes().getNamedItem("url").getNodeValue());
            }
        }
        this.getBatches().add(theBatch);
    }

    private void readCandidateNode(final Node node) {
        this.candidateNodes.add(node);
    }

    private void readColumnNode(final Node node) {
        Column newColumn = null;
        Label_0054:
        {
            if (node.getAttributes().getNamedItem("computation") != null) {
                try {
                    newColumn = AggregateFactory.getAggregateFromNode(node, this);
                    break Label_0054;
                } catch (ClassNotFoundException e) {
                    ListDef.LOG.error((Object) e.getMessage(), (Throwable) e);
                    return;
                }
            }
            newColumn = new Column();
            newColumn.fromNode(node);
        }
        this.columns.add(newColumn);
        this.columnsByName.put(newColumn.getName(), newColumn);
    }

    private void readFilterNode(final Node node) {
        final Filter newFilter = new Filter();
        newFilter.fromNode(node);
        this.filters.add(newFilter);
    }

    private void readGroupNode(final Node node) {
        this.setGrouped(true);
        this.rootGroup.fromNode(node);
        this.aggregated = (this.aggregated || this.rootGroup.isAggregated());
    }

    private void readParameterNode(final Node node) {
        final Parameter parameter = ParameterFactory.getParameterFromNode(node, this);
        this.parameters.put(parameter.getName(), parameter);
        this.orderedParameters.add(parameter.getName());
    }

    public void setAutoForward(final String autoForward) {
        this.autoForward = autoForward;
    }

    public void setCharted(final boolean charted) {
        this.rootGroup.setCharted(charted);
    }

    public void setDefaultSort(final String defaultOrder, final String defaultOrderType) {
        for (final Column aColumn : this.columns) {
            if (aColumn.getName().equals(defaultOrder)) {
                aColumn.setOrdered(true);
                aColumn.setOrderType((defaultOrderType == null) ? "a" : defaultOrderType);
                Logger.debug("\tDefault order configured on column " + aColumn.getName());
            } else {
                aColumn.setOrdered(false);
            }
        }
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDialogType(final String dialogType) {
        this.dialogType = dialogType;
    }

    final void setExported(final boolean exported) {
        this.exported = exported;
    }

    public void setForceDisplaySearch(final boolean forceDisplaySearch) {
        this.forceDisplaySearch = forceDisplaySearch;
    }

    public final void setGrouped(final boolean grouped) {
        this.grouped = grouped;
    }

    public void setHugeMode(final boolean hugeMode) {
        this.hugeMode = hugeMode;
    }

    public void setListKeys(final String listKeys) {
        this.listKeys = listKeys;
    }

    public void setMessage(final String string) {
        this.message = string;
    }

    public void setOnSelectionChangedScript(final String onSelectionChangedScript) {
        this.onSelectionChangedScript = onSelectionChangedScript;
    }

    public void setOnSelectionClearedScript(final String onSelectionClearedScript) {
        this.onSelectionClearedScript = onSelectionClearedScript;
    }

    public void setOrderIndex(final int newOrderIndex) {
        this.orderIndex = newOrderIndex;
    }

    public void setOrderType(final String newOrderType) {
        this.orderType = newOrderType;
    }

    public void setQueryType(final String queryType) {
        this.queryType = queryType;
    }

    public void setReauthFailureMessage(final String reauthFailureMessage) {
        this.reauthFailureMessage = reauthFailureMessage;
    }

    public void setReauthPassed(final boolean isReauthPassed) {
        this.isReauthPassed = isReauthPassed;
    }

    public void setReportId(final String reportId) {
        this.reportId = reportId;
    }

    public void setScript(final String script) {
        this.script = script;
    }

    public void setSearchColumns(final int searchColumns) {
        this.searchColumns = searchColumns;
    }

    public void setShowTitle(final boolean showTitle) {
        this.showTitle = showTitle;
    }

    public void setSubscribeTopic(final String subscribeTopic) {
        this.subscribeTopic = subscribeTopic;
    }

    public void setTemplateId(final String templateId) {
        this.templateId = templateId;
    }

    public void setViewer(final Viewer viewer) {
        this.viewer = viewer;
    }

    public void setViewerType(final String viewerType) {
        this.viewerType = viewerType;
        if (this.viewer != null) {
            this.setExported(this.viewer.isPaginal());
        } else {
            if (viewerType == null) {
                throw new NullPointerException("Can't set a null viewer type.");
            }
            this.setExported(!ViewerFactory.newViewer(viewerType).isPaginal());
        }
    }

    public void severalBatchAction(final RunData aRunData) {
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "severalBatchAction");
        final URLListKeys list = new URLListKeys(aRunData.getParameters().getString("list_keys"));
        final GTPUser user = (GTPUser) aRunData.getUser();
        TransactionProductFile tnxFile = null;
        try {
            final Integer batchId = BatchIdGenerator.generate();
            aRunData.getParameters().add("batch_id", batchId);
            try {
                Audit.audit(aRunData, "Y");
                PoolBrokerService.beginTransaction();
                String curCode = "";
                final com.misys.portal.product.common.Batch batch = new com.misys.portal.product.common.Batch();
                batch.setBatch_id(new IdField(batchId));
                BigDecimal totalAmt = new BigDecimal(0);
                int count = 0;
                String entityAbbvName = "";
                for (int i = 0; i < list.size(); ++i) {
                    final String prodCode = list.get(i, 2);
                    final String subProdCode = list.get(i, 3);
                    tnxFile = null;
                    if (Arrays.asList(ListDef.subProdsForBuilder).contains(subProdCode)) {
                        final IProductFileBuilder productFileBuilder = (IProductFileBuilder) TransactionProductFileBuilder.withProductCode(prodCode).withSubProductCode(subProdCode).build();
                        tnxFile = productFileBuilder.getProduct();
                    } else {
                        tnxFile = ProductFactory.newTransactionProductFile(prodCode);
                    }
                    tnxFile.retrieveFileFromTnx(list.get(i, 0), list.get(i, 1));
                    tnxFile.getTransactionProduct().getBatch_id().setValue(batchId);
                    if (tnxFile.getTnx_amt() != null && tnxFile.getTnx_amt().getValue() != null) {
                        totalAmt = totalAmt.add(tnxFile.getTnx_amt().getValue());
                    }
                    curCode = tnxFile.getTnx_cur_code().getStringValue();
                    entityAbbvName = tnxFile.getEntity().getStringValue();
                    tnxFile.save();
                    ++count;
                }
                if (count > 0) {
                    batch.getBatch_cur_code().setValue(curCode);
                    batch.getBatch_amt().setValue(totalAmt);
                    batch.getBatch_size().setValue(count);
                    batch.getBatch_maker_id().setValue(user.getId());
                    batch.getEntity_abbv_name().setValue((!StringUtils.isEmpty(entityAbbvName) && StringUtils.isEmpty(entityAbbvName.trim())) ? null : entityAbbvName);
                    batch.getCompany_id().setValue(user.getCompanyId());
                    batch.getBatch_dttm().setValue(new Timestamp(System.currentTimeMillis()));
                    final StringField batchType = new StringField(2);
                    batchType.setValue("01");
                    batch.setBatch_type(batchType);
                    batch.saveIntoDB();
                }
                PoolBrokerService.commitTransaction();
                Audit.updateContext(aRunData, (Auditable) tnxFile, "Y");
            } catch (Exception e) {
                Audit.audit(aRunData, "E");
                try {
                    PoolBrokerService.rollbackTransaction();
                } catch (Exception ee) {
                    ListDef.LOG.error((Object) ee.getMessage(), (Throwable) ee);
                    new GTPException((RunData) null, "Save File, Unable to Rollback Transaction:", (Throwable) ee);
                }
                throw new GTPException((RunData) null, "Save File, Exception:", (Throwable) e);
            } finally {
                Audit.saveContext(aRunData);
            }
            Audit.saveContext(aRunData);
        } catch (GTPException e2) {
            ListDef.LOG.error((Object) e2.getMessage(), (Throwable) e2);
            new GTPException("Error severalBatchAction");
        }
        profiler.stop((Class) this.getClass(), "severalBatchAction");
    }

    public void severalCancelAction(final RunData aRunData) {
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "severalCancelAction");
        final URLListKeys list = new URLListKeys(this.listKeys);
        TransactionProductFile tnxFile = null;
        Vector newReference = null;
        final GTPUser user = (GTPUser) aRunData.getUser();
        if (this.acceptedFiles == null) {
            this.acceptedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.failedFiles == null) {
            this.failedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        final List<TransactionProductFile> tnxFileList = new ArrayList<TransactionProductFile>();
        try {
            for (int i = 0; i < list.size(); ++i) {
                tnxFile = ProductFactory.newTransactionProductFile(list.get(i, 2));
                tnxFile.retrieveFileFromTnx(list.get(i, 0), list.get(i, 1));
                tnxFileList.add(tnxFile);
            }
            if (GTPSecurityCheck.isReauthenticationEnabled(true)) {
                final String reAuthType = GTPSecurityCheck.getReAuthType(user, "reject", this.getReAuthConfigParams("01"));
                if (!"01".equals(reAuthType)) {
                    final GTPAuthentication authentication = GTPSecurityCheck.reauthenticate(aRunData, user, GTPSecurity.getReAuthCredentials(aRunData, (List) tnxFileList), reAuthType);
                    if (!authentication.isAuthenticated()) {
                        final String serverMessage = Localization.getGTPString(user.getLanguage(), "ERROR_MESSAGE_REAUTH_FAILED");
                        this.setReauthPassed(false);
                        this.setReauthFailureMessage(serverMessage);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            GTPException.log("Error in ListDef processing", e);
        }
        for (int i = 0; i < tnxFileList.size(); ++i) {
            tnxFile = tnxFileList.get(i);
            try {
                if (!GTPSecurityCheck.checkProductContext(aRunData, tnxFile.getRef_id().getValue(), tnxFile.getProduct_code().getValue(), tnxFile.getTnx_id().getValue(), true)) {
                    throw new GTPException(aRunData, "Security Exception, Several cancel FT, ref_id=" + tnxFile.getRef_id());
                }
                final Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                tnxFile.getRelease_user_id().setValue(user.getId());
                tnxFile.getRelease_dttm().setValue(currentDate);
                tnxFile.getTnx_stat_code().setValue("09");
                tnxFile.getProd_stat_code().setValue("06");
                tnxFile.getSub_tnx_stat_code().setValue("16");
                final MasterProductFile masterFile = ProductFactory.newMasterProductFile(tnxFile);
                masterFile.updateVersion();
                masterFile.save();
                final IntegerField destVersion = new IntegerField(masterFile.getVersion().getValue());
                tnxFile.setDest_master_version(destVersion);
                tnxFile.getProduct().saveIntoDB();
                newReference = new Vector();
                newReference.add(0, tnxFile.getRef_id().getValue());
                this.acceptedFiles.put(tnxFile, new GTPAuthorisation(true));
                newReference.add(1, "A");
                Audit.updateContext(aRunData, (Auditable) tnxFile);
            } catch (GTPException gtpe) {
                if (tnxFile != null) {
                    newReference = new Vector();
                    newReference.add(0, tnxFile.getRef_id());
                    newReference.add(1, "E");
                    Audit.updateContext(aRunData, (Auditable) tnxFile, "E");
                    if (list.get(i, 0).equals(tnxFile.getRef_id().getValue()) && list.get(i, 1).equals(tnxFile.getTnx_id().getValue())) {
                        this.failedFiles.put(tnxFile, new GTPAuthorisation(true));
                    }
                }
                ListDef.LOG.error((Object) gtpe.getMessage(), (Throwable) gtpe);
            } catch (Exception e2) {
                ListDef.LOG.error((Object) e2.getMessage(), (Throwable) e2);
                new GTPException("Error: multiple cancel");
            }
        }
        if (!this.acceptedFiles.isEmpty() && this.failedFiles.isEmpty()) {
            Audit.audit(aRunData, "Y");
        } else if (this.acceptedFiles.isEmpty() && !this.failedFiles.isEmpty()) {
            Audit.audit(aRunData, "E");
        } else {
            Audit.audit(aRunData, "M");
        }
        Audit.saveContext(aRunData);
        profiler.stop((Class) this.getClass(), "severalCancelAction");
    }

    public void severalDeleteAction(final RunData aRunData) {
        final GTPAuthorisation authorised = new GTPAuthorisation(true);
        final URLListKeys list = new URLListKeys(this.listKeys);
        if (this.acceptedFiles == null) {
            this.acceptedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.rejectedFiles == null) {
            this.rejectedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.failedFiles == null) {
            this.failedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        for (int i = 0; i < list.size(); ++i) {
            try {
                if (list.get(i, 1) != null) {
                    final TransactionProductFile file = ProductFactory.newTransactionProductFile(list.get(i, 2));
                    file.retrieveFileFromTnx(list.get(i, 0), list.get(i, 1));
                    if (!GTPSecurityCheck.checkProductContext(aRunData, file.getRef_id().getValue(), file.getProduct_code().getValue(), file.getTnx_id().getValue(), true)) {
                        throw new GTPException(aRunData, "Security Exception, Several Delete, ref_id=" + file.getRef_id());
                    }
                    file.deleteFile(aRunData);
                    this.acceptedFiles.put(file, authorised);
                    Audit.updateContext(aRunData, (Auditable) file);
                } else {
                    final MasterProductFile file2 = ProductFactory.newMasterProductFile(list.get(i, 2));
                    file2.retrieveFileFromExisting(list.get(i, 0));
                    if (!GTPSecurityCheck.checkProductContext(aRunData, file2.getRef_id().getValue(), file2.getProduct_code().getValue(), null, true)) {
                        throw new GTPException(aRunData, "Security Exception, Several Delete, ref_id=" + file2.getRef_id());
                    }
                    file2.deleteFile();
                    this.acceptedFiles.put((ProductFile) file2, authorised);
                    Audit.updateContext(aRunData, (Auditable) file2);
                }
                ++this.nbRecordsDeleted;
            } catch (GTPException e) {
                ListDef.LOG.error((Object) e.getMessage(), (Throwable) e);
            }
        }
        if (!this.acceptedFiles.isEmpty()) {
            aRunData.getTemplateInfo().setTemp("__interface_data_source", (Object) this.acceptedFiles);
        }
        Audit.audit(aRunData, "Y");
        Audit.saveContext(aRunData);
    }

    public void severalForwardAction(final RunData aRunData) {
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "severalForwardAction");
        final URLListKeys list = new URLListKeys(this.listKeys);
        TransactionProductFile tnxFile = null;
        Vector newReference = null;
        final Vector allFiles = new Vector();
        final GTPUser user = (GTPUser) aRunData.getUser();
        if (this.acceptedFiles == null) {
            this.acceptedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.rejectedFiles == null) {
            this.rejectedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.failedFiles == null) {
            this.failedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        for (int i = 0; i < list.size(); ++i) {
            try {
                tnxFile = ProductFactory.newTransactionProductFile(list.get(i, 2));
                tnxFile.retrieveFileFromTnx(list.get(i, 0), list.get(i, 1));
                final IntegerField forwarder = (IntegerField) BulkFieldFactory.newAdditionalField("integer");
                forwarder.setName("forwarder");
                forwarder.setValue(user.getId());
                tnxFile.getProduct().getObjectData().addField(forwarder.getName(), (BulkField) forwarder);
                if (!GTPSecurityCheck.checkProductContext(aRunData, tnxFile.getRef_id().getValue(), tnxFile.getProduct_code().getValue(), tnxFile.getTnx_id().getValue(), true) && GTPSecurityCheck.hasPermission(((GTPRunData) aRunData).getUserAcl(), "tradeadmin_forward_access", ((GTPUser) aRunData.getUser()).getEntities())) {
                    throw new GTPException(aRunData, "Security Exception, forward " + tnxFile.getProduct_code().getValue() + ", ref_id=" + tnxFile.getRef_id());
                }
                Timestamp oldCtlDttm;
                if (DefaultResourceProvider.CUSTOMER_COUNTERPARTY_TYPE_LIST.contains(user.getCompanyType())) {
                    oldCtlDttm = tnxFile.getCtl_dttm().getValue();
                } else {
                    oldCtlDttm = tnxFile.getBo_ctl_dttm().getValue();
                }
                if (!GTPSecurityCheck.checkTransactionContext(aRunData, tnxFile, oldCtlDttm)) {
                    throw new GTPException(aRunData, "The file status has changed, you should refresh your transaction details.");
                }
                final GTPAuthorisation authorised = new GTPAuthorisation(true);
                try {
                    PoolBrokerService.beginTransaction();
                    tnxFile.getProduct().saveIntoDB();
                    PoolBrokerService.commitTransaction();
                } catch (Exception e) {
                    try {
                        PoolBrokerService.rollbackTransaction();
                    } catch (Exception ee) {
                        ListDef.LOG.error((Object) ee.getMessage(), (Throwable) ee);
                        final GTPException ex = new GTPException((RunData) null, "Save File, Unable to Rollback Transaction:" + tnxFile.getRef_id() + "/" + tnxFile.getTnx_id(), (Throwable) ee);
                    }
                    throw new GTPException((RunData) null, "Save File, Exception:" + tnxFile.getRef_id() + "/" + tnxFile.getTnx_id(), (Throwable) e);
                }
                newReference = new Vector();
                newReference.add(0, tnxFile.getRef_id().getValue());
                if (authorised.isAuthorised()) {
                    this.acceptedFiles.put(tnxFile, authorised);
                    newReference.add(1, "A");
                    Audit.updateContext(aRunData, (Auditable) tnxFile, "A");
                } else {
                    this.rejectedFiles.put(tnxFile, authorised);
                    newReference.add(1, "F");
                    Audit.updateContext(aRunData, (Auditable) tnxFile, "F");
                }
                allFiles.add(tnxFile);
            } catch (GTPException gtpe) {
                if (tnxFile != null) {
                    newReference = new Vector();
                    newReference.add(0, tnxFile.getRef_id());
                    newReference.add(1, "E");
                    Audit.updateContext(aRunData, (Auditable) tnxFile, "E");
                    if (list.get(i, 0).equals(tnxFile.getRef_id().getValue()) && list.get(i, 1).equals(tnxFile.getTnx_id().getValue())) {
                        this.failedFiles.put(tnxFile, new GTPAuthorisation(true));
                    }
                }
                ListDef.LOG.error((Object) gtpe.getMessage(), (Throwable) gtpe);
            }
        }
        if (!allFiles.isEmpty()) {
            aRunData.getTemplateInfo().setTemp("__interface_data_source", (Object) allFiles);
        }
        if (!this.acceptedFiles.isEmpty() && this.rejectedFiles.isEmpty() && this.failedFiles.isEmpty()) {
            Audit.audit(aRunData, "A");
        } else if (this.acceptedFiles.isEmpty() && !this.rejectedFiles.isEmpty() && this.failedFiles.isEmpty()) {
            Audit.audit(aRunData, "F");
        } else if (this.acceptedFiles.isEmpty() && this.rejectedFiles.isEmpty() && !this.failedFiles.isEmpty()) {
            Audit.audit(aRunData, "E");
        } else {
            Audit.audit(aRunData, "M");
        }
        Audit.saveContext(aRunData);
        profiler.stop((Class) this.getClass(), "severalForwardAction");
    }

    public void severalGroupAction(final RunData aRunData) {
        final URLListKeys list = new URLListKeys(this.listKeys);
        for (int i = 0; i < list.size(); ++i) {
            try {
                if (list.get(i, 1) != null) {
                    final TransactionProductFile file = ProductFactory.newTransactionProductFile(list.get(i, 2));
                    file.retrieveFileFromTnx(list.get(i, 0), list.get(i, 1));
                    this.acceptedFiles.put(file, new GTPAuthorisation(true));
                    Audit.updateContext(aRunData, (Auditable) file);
                }
                ++this.nbRecordsGrouped;
            } catch (GTPException e) {
                ListDef.LOG.error((Object) e.getMessage(), (Throwable) e);
            }
            final Vector newReference = new Vector();
            newReference.add(0, list.get(i, 0));
            newReference.add(1, "Y");
        }
        if (!this.acceptedFiles.isEmpty()) {
            aRunData.getTemplateInfo().setTemp("__interface_data_source", (Object) this.acceptedFiles);
        }
        Audit.audit(aRunData, "Y");
        Audit.saveContext(aRunData);
    }

    public void severalResubmitAction(final RunData aRunData) {
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "severalResubmitAction");
        final URLListKeys list = new URLListKeys(this.listKeys);
        GTPAuthorisation authorised = null;
        Vector newReference = null;
        final Vector allFiles = new Vector();
        final GTPUser user = (GTPUser) aRunData.getUser();
        if (this.acceptedFiles == null) {
            this.acceptedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.rejectedFiles == null) {
            this.rejectedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.failedFiles == null) {
            this.failedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.businessValidationRejectFiles == null) {
            this.businessValidationRejectFiles = new HashMap<ProductFile, GTPBusinessValidationException>();
        }
        Audit.audit(aRunData, "Y");
        final List<TransactionProductFile> tnxFileList = new ArrayList<TransactionProductFile>();
        String tnxTypeCode = null;
        try {
            boolean isAuthorizedReauth = false;
            for (int i = 0; i < list.size(); ++i) {
                final TransactionProductFile tnxFile = ProductFactory.newTransactionProductFile(list.get(i, 2));
                tnxFile.retrieveFileFromTnx(list.get(i, 0), list.get(i, 1));
                tnxFileList.add(tnxFile);
                if ("CN".equals(tnxFile.getProduct_code().getStringValue())) {
                    tnxFile.setNewTnxId();
                    tnxFile.getBo_ref_id().setValue((String) null);
                    tnxFile.getBo_tnx_id().setValue((String) null);
                    tnxFile.getProd_stat_code().setValue("55");
                    tnxFile.getTnx_type_code().setValue("21");
                    tnxFile.getTnx_stat_code().setValue("04");
                    final CrossReference crossRef = new CrossReference();
                    crossRef.getRef_id().setValue(list.get(i, 0));
                    crossRef.getTnx_id().setValue(list.get(i, 1));
                    crossRef.getChild_ref_id().setValue(tnxFile.getRef_id().getStringValue());
                    crossRef.getChild_tnx_id().setValue(tnxFile.getTnx_id().getStringValue());
                    crossRef.getProduct_code().setValue("CN");
                    crossRef.getChild_product_code().setValue("CN");
                    crossRef.getType_code().setValue("03");
                    tnxFile.getCrossReferences().add(crossRef);
                }
                if (DefaultResourceProvider.REAUTH_LAST_AUTHORIZER_ENABLED && !isAuthorizedReauth) {
                    authorised = tnxFile.signProductFile(aRunData, "RESUBMIT", tnxFile.getTnx_type_code().getValue());
                    isAuthorizedReauth = authorised.isAuthorised();
                }
            }
            if (GTPSecurityCheck.isReauthenticationEnabled(isAuthorizedReauth)) {
                final String reAuthType = GTPSecurityCheck.getReAuthType(user, "submit", this.getReAuthConfigParams("21"));
                if (!"01".equals(reAuthType)) {
                    final GTPAuthentication authentication = GTPSecurityCheck.reauthenticate(aRunData, user, GTPSecurity.getReAuthCredentials(aRunData, (List) tnxFileList), reAuthType);
                    if (!authentication.isAuthenticated()) {
                        final String serverMessage = Localization.getGTPString(user.getLanguage(), "ERROR_MESSAGE_REAUTH_FAILED");
                        this.setReauthPassed(false);
                        this.setReauthFailureMessage(serverMessage);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            GTPException.log("Error in ListDef processing", e);
        }
        for (int j = 0; j < tnxFileList.size(); ++j) {
            final TransactionProductFile tnxFile2 = tnxFileList.get(j);
            try {
                if (!GTPSecurityCheck.checkProductContext(aRunData, tnxFile2.getRef_id().getValue(), tnxFile2.getProduct_code().getValue(), tnxFile2.getTnx_id().getValue(), true)) {
                    Audit.audit(aRunData, "C");
                    throw new GTPException(aRunData, "Security Exception, Several Submit Product=" + list.get(j, 2) + ", ref_id=" + tnxFile2.getRef_id());
                }
                newReference = new Vector();
                newReference.add(0, tnxFile2.getRef_id().getValue());
                authorised = tnxFile2.signProductFile(aRunData, "RESUBMIT", tnxFile2.getTnx_type_code().getValue());
                if ("CN".equals(tnxFile2.getProduct_code().getStringValue())) {
                    tnxTypeCode = "21";
                }
                tnxFile2.saveFile(authorised, list.get(j, 2), "SUBMIT", "RESUBMIT", tnxTypeCode, user);
                if (authorised.isAuthorised()) {
                    this.acceptedFiles.put(tnxFile2, authorised);
                    newReference.add(1, "A");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "A");
                } else {
                    this.rejectedFiles.put(tnxFile2, authorised);
                    newReference.add(1, "F");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "F");
                }
                allFiles.add(tnxFile2);
            } catch (GTPBusinessValidationException bve) {
                if (tnxFile2 != null) {
                    newReference = new Vector();
                    newReference.add(0, tnxFile2.getRef_id());
                    newReference.add(1, "BE");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "BE");
                    this.businessValidationRejectFiles.put(tnxFile2, bve);
                }
                Logger.error(bve.getMessage());
            } catch (GTPException gtpe) {
                if (tnxFile2 != null) {
                    newReference = new Vector();
                    newReference.add(0, tnxFile2.getRef_id());
                    newReference.add(1, "E");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "E");
                    if (list.get(j, 0).equals(tnxFile2.getRef_id().getValue()) && list.get(j, 1).equals(tnxFile2.getTnx_id().getValue())) {
                        this.failedFiles.put(tnxFile2, authorised);
                    }
                }
                ListDef.LOG.error((Object) gtpe.getMessage(), (Throwable) gtpe);
            } catch (Exception e2) {
                if (tnxFile2 != null) {
                    newReference = new Vector();
                    newReference.add(0, tnxFile2.getRef_id());
                    newReference.add(1, "E");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "E");
                    if (list.get(j, 0).equals(tnxFile2.getRef_id().getValue()) && list.get(j, 1).equals(tnxFile2.getTnx_id().getValue())) {
                        this.failedFiles.put(tnxFile2, authorised);
                    }
                }
                ListDef.LOG.error((Object) e2.getMessage(), (Throwable) e2);
            }
        }
        if (!allFiles.isEmpty()) {
            aRunData.getTemplateInfo().setTemp("__interface_data_source", (Object) allFiles);
        }
        if (!this.acceptedFiles.isEmpty() && this.rejectedFiles.isEmpty() && this.failedFiles.isEmpty() && this.businessValidationRejectFiles.isEmpty()) {
            Audit.audit(aRunData, "A");
        } else if (this.acceptedFiles.isEmpty() && !this.rejectedFiles.isEmpty() && this.failedFiles.isEmpty() && this.businessValidationRejectFiles.isEmpty()) {
            Audit.audit(aRunData, "F");
        } else if (this.acceptedFiles.isEmpty() && this.rejectedFiles.isEmpty() && !this.failedFiles.isEmpty() && this.businessValidationRejectFiles.isEmpty()) {
            Audit.audit(aRunData, "E");
        } else if (this.acceptedFiles.isEmpty() && this.rejectedFiles.isEmpty() && this.failedFiles.isEmpty() && !this.businessValidationRejectFiles.isEmpty()) {
            Audit.audit(aRunData, "BE");
        } else {
            Audit.audit(aRunData, "M");
        }
        Audit.saveContext(aRunData);
        profiler.stop((Class) this.getClass(), "severalReSubmitAction");
    }

    public void severalSubmitAction(final RunData aRunData) {
        this.runData = aRunData;
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "severalSubmitAction");
        final URLListKeys list = new URLListKeys(this.listKeys);
        GTPAuthorisation authorised = null;
        Vector newReference = null;
        final Vector allFiles = new Vector();
        final GTPUser user = (GTPUser) aRunData.getUser();
        if (this.acceptedFiles == null) {
            this.acceptedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.rejectedFiles == null) {
            this.rejectedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.failedFiles == null) {
            this.failedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.autoForwardedFiles == null) {
            this.autoForwardedFiles = new HashMap<ProductFile, GTPAuthorisation>();
        }
        if (this.businessValidationRejectFiles == null) {
            this.businessValidationRejectFiles = new HashMap<ProductFile, GTPBusinessValidationException>();
        }
        Audit.audit(aRunData, "Y");
        final List<TransactionProductFile> tnxFileList = new ArrayList<TransactionProductFile>();
        boolean isAuthorizedForReauth = false;
        try {
            for (int i = 0; i < list.size(); ++i) {
                final String productCode = list.get(i, 2);
                final String subProductCode = list.get(i, 3);
                TransactionProductFile tnxFile = null;
                if (Arrays.asList(ListDef.subProdsForBuilder).contains(subProductCode)) {
                    final IProductFileBuilder productFileBuilder = (IProductFileBuilder) TransactionProductFileBuilder.withProductCode(productCode).withSubProductCode(subProductCode).build();
                    tnxFile = productFileBuilder.getProduct();
                } else {
                    tnxFile = ProductFactory.newTransactionProductFile(productCode);
                }
                if (tnxFile instanceof BulkFile) {
                    ((BulkFile) tnxFile).setChildTnxLoaded(true);
                }
                tnxFile.retrieveFileFromTnx(list.get(i, 0), list.get(i, 1));
                if (!isAuthorizedForReauth) {
                    if (DefaultResourceProvider.REAUTH_LAST_AUTHORIZER_ENABLED) {
                        final GTPAuthorisation authorized = tnxFile.signProductFile(aRunData, "UNSIGNED", tnxFile.getTnx_type_code().getStringValue());
                        isAuthorizedForReauth = authorized.isAuthorised();
                    } else {
                        isAuthorizedForReauth = true;
                    }
                }
                tnxFileList.add(tnxFile);
            }
            if (GTPSecurityCheck.isReauthenticationEnabled(isAuthorizedForReauth)) {
                final String reAuthType = GTPSecurityCheck.getReAuthType(user, "submit", this.getReAuthConfigParams("01"));
                if (!"01".equals(reAuthType)) {
                    final GTPAuthentication authentication = GTPSecurityCheck.reauthenticate(aRunData, user, GTPSecurity.getReAuthCredentials(aRunData, (List) tnxFileList), reAuthType);
                    if (!authentication.isAuthenticated()) {
                        final String serverMessage = Localization.getGTPString(user.getLanguage(), "ERROR_MESSAGE_REAUTH_FAILED");
                        this.setReauthPassed(false);
                        this.setReauthFailureMessage(serverMessage);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            GTPException.log("Error in ListDef processing", e);
        }
        String userBankTimeZone = String.valueOf(aRunData.getSession().getAttribute("turbine.AccessControlList.userBankTimeZone"));
        for (int j = 0; j < tnxFileList.size(); ++j) {
            final TransactionProductFile tnxFile2 = tnxFileList.get(j);
            try {
                boolean isAutoForwarded = false;
                if (!GTPSecurityCheck.checkProductContext(aRunData, tnxFile2.getRef_id().getValue(), tnxFile2.getProduct_code().getValue(), tnxFile2.getTnx_id().getValue(), true)) {
                    Audit.audit(aRunData, "C");
                    throw new GTPException(aRunData, "Security Exception, Several Submit Product=" + list.get(j, 2) + ", ref_id=" + tnxFile2.getRef_id());
                }
                Timestamp oldCtlDttm = null;
                if (DefaultResourceProvider.CUSTOMER_COUNTERPARTY_TYPE_LIST.contains(user.getCompanyType()) && tnxFile2.getCtl_dttm() != null) {
                    oldCtlDttm = tnxFile2.getCtl_dttm().getValue();
                } else if (tnxFile2.getBo_ctl_dttm() != null) {
                    oldCtlDttm = tnxFile2.getBo_ctl_dttm().getValue();
                }
                if (oldCtlDttm != null && !GTPSecurityCheck.checkTransactionContext(aRunData, tnxFile2, oldCtlDttm)) {
                    Audit.audit(aRunData, "C");
                    throw new GTPException(aRunData, "The file status has changed, you should refresh your transaction details.");
                }
                newReference = new Vector();
                newReference.add(0, tnxFile2.getRef_id().getValue());
                authorised = tnxFile2.signProductFile(aRunData, "UNSIGNED", tnxFile2.getTnx_type_code().getValue());
                boolean isPABNotAllowed = false;
                boolean isHolidayCutOffInValid = false;
                if (!GTPSecurity.isBank((User) user) && authorised.isAuthorised() && "TD".equals(tnxFile2.getProduct_code().getStringValue()) && "TRTD".equals(tnxFile2.getSub_product_code().getStringValue())) {
                    final TermDepositFile theFile = (TermDepositFile) tnxFile2;
                    final GTPCompany bank = GTPSecurity.getCompany(theFile.getIssuing_bank().getAbbv_name().getStringValue());
                    userBankTimeZone = ((bank != null) ? bank.getTimeZone() : userBankTimeZone);
                    StandingInstructions instructions = new StandingInstructions((TransactionProductFile) theFile);
                    instructions.setTnx_id(theFile.getTnx_id().getValue());
                    instructions = TreasuryProcessingServiceFactory.getService(TreasuryProcessingServiceFactory.SERVICETYPE.TD).submitStandingInstructions(instructions);
                } else if (!GTPSecurity.isBank((User) user) && authorised.isAuthorised() && "TD".equalsIgnoreCase(tnxFile2.getProduct_code().toString())) {
                    final TermDepositFile theFile = (TermDepositFile) tnxFile2;
                    final GTPCompany bank = GTPSecurity.getCompany(theFile.getIssuing_bank().getAbbv_name().getStringValue());
                    userBankTimeZone = ((bank != null) ? bank.getTimeZone() : userBankTimeZone);
                    final String tnxTypeCode = theFile.getTnx_type_code().toString();
                    final String valueDate = theFile.getTermDeposit().getValue_date().getStringValue();
                    if ("01".equals(tnxTypeCode) && valueDate != null && valueDate.length() > 0) {
                        final boolean isValid = theFile.isValueDateValid(theFile, aRunData);
                        if (!isValid) {
                            theFile.getTnx_stat_code().setValue("53");
                            theFile.getSub_tnx_stat_code().setValue("08");
                            final Date date = Utils.getUserDateTime(userBankTimeZone);
                            final Timestamp currentDate = new Timestamp(date.getTime());
                            theFile.getRelease_user_id().setValue(user.getId());
                            theFile.getRelease_dttm().setValue(currentDate);
                            theFile.getProd_stat_code().setValue("01");
                            theFile.getProduct().saveIntoDB();
                            throw new GTPBusinessValidationException("Value Date is less than Creation Date.So transaction is getting rejected", "VALUE_DATE_GREATER_OR_EQUAL_APPLICATION_DATE");
                        }
                        if (!"14".equals(theFile.getTnx_type_code().getStringValue()) && "Y".equals(this.getAutoForward())) {
                            isAutoForwarded = TermDepositUtils.autoForwardTheDatesAndSetPostDatedFlag(theFile, aRunData, true);
                        } else if (!"14".equals(theFile.getTnx_type_code().getStringValue()) && !TermDepositUtils.validateHolidayCutOffAndSetPostDatedFlag(theFile, aRunData, true)) {
                            isHolidayCutOffInValid = true;
                        }
                    }
                } else if (!GTPSecurity.isBank((User) user) && authorised.isAuthorised() && "FX".equals(tnxFile2.getProduct_code().getStringValue())) {
                    final ForeignExchangeFile theFile2 = (ForeignExchangeFile) tnxFile2;
                    final GTPCompany bank = GTPSecurity.getCompany(theFile2.getIssuing_bank().getAbbv_name().getStringValue());
                    userBankTimeZone = ((bank != null) ? bank.getTimeZone() : userBankTimeZone);
                    StandingInstructions instructions = new StandingInstructions((TransactionProductFile) theFile2);
                    instructions.setTnx_id(theFile2.getTnx_id().getValue());
                    instructions = TreasuryProcessingServiceFactory.getService(TreasuryProcessingServiceFactory.SERVICETYPE.FX).submitStandingInstructions(instructions);
                } else if (!GTPSecurity.isBank((User) user) && authorised.isAuthorised() && "FT".equals(tnxFile2.getProduct_code().getStringValue()) && ("TRTPT".equals(tnxFile2.getSub_product_code().getStringValue()) || "TRINT".equals(tnxFile2.getSub_product_code().getStringValue()))) {
                    final FundTransferFile theFile3 = (FundTransferFile) tnxFile2;
                    final GTPCompany bank = GTPSecurity.getCompany(theFile3.getIssuing_Bank().getAbbv_name().getStringValue());
                    userBankTimeZone = ((bank != null) ? bank.getTimeZone() : userBankTimeZone);
                    final FTDataBean ftData = new FTDataBean((com.misys.portal.cash.product.ft.common.FundTransferFile) theFile3);
                    ftData.setAuthorized(authorised.isAuthorised());
                    ftData.setMode("UNSIGNED");
                    TreasuryUtils.setxCCYInd(theFile3, ftData);
                    if (theFile3.getFundTransfer().getObjectData().getField("rec_id") != null && !theFile3.getFundTransfer().getObjectData().getField("rec_id").isNull()) {
                        ftData.setRecId(theFile3.getFundTransfer().getObjectData().getField("rec_id").getStringValue());
                    }
                    ((FTProcessingService) TurbineServices.getInstance().getService("FTProcessingService")).acceptFundTransfer(ftData);
                    TreasuryUtils.updateAcceptFileFromFTData(theFile3, ftData);
                    if (2 == ftData.getStatus()) {
                        theFile3.getProd_stat_code().setValue("03");
                        theFile3.getTnx_stat_code().setValue("04");
                    } else if (!authorised.isAuthorised() && ftData.getStatus() == 11) {
                        theFile3.getProd_stat_code().setValue("02");
                    }
                } else if (!GTPSecurity.isBank((User) user) && authorised.isAuthorised() && "FT".equals(tnxFile2.getProduct_code().getStringValue()) && "DOM".equals(tnxFile2.getSub_product_code().getStringValue())) {
                    final FundTransfer theProduct = (FundTransfer) tnxFile2.getProduct();
                    final ObjectData theObjData = theProduct.getObjectData();
                    final Integer accountId = Utils.getAccountIdForCustomer(theProduct.getApplicant_act_no().toString(), user.getCompanyId());
                    Integer entityId = null;
                    if (!theProduct.getEntity().toString().isEmpty()) {
                        entityId = user.getEntity(theProduct.getEntity().toString()).getEntity_id().getValue();
                    }
                    if (theObjData.getField("applicant_act_pab").getStringValue().equals("N")) {
                        final UserAccountSet userAccountSet = user.getAccounts();
                        userAccountSet.retrieveFromDB();
                        final List<String> accountPAB = userAccountSet.getAccountProperties(accountId, entityId, "PAB");
                        if (!accountPAB.isEmpty()) {
                            isPABNotAllowed = true;
                        }
                    } else if (theObjData.getField("applicant_act_pab").getStringValue().equals("Y")) {
                        final UserAccountSet userAccountSet = user.getAccounts();
                        userAccountSet.retrieveFromDB();
                        final List<String> accountPAB = userAccountSet.getAccountProperties(accountId, entityId, "PAB");
                        if (accountPAB.isEmpty()) {
                            isPABNotAllowed = true;
                        }
                    }
                }
                if ("FT".equals(tnxFile2.getProduct_code().getStringValue())) {
                    final com.misys.portal.cash.product.ft.common.FundTransferFile ftFile = (com.misys.portal.cash.product.ft.common.FundTransferFile) tnxFile2;
                    final GTPCompany bank = GTPSecurity.getCompany(ftFile.getIssuing_Bank().getAbbv_name().getStringValue());
                    userBankTimeZone = ((bank != null) ? bank.getTimeZone() : userBankTimeZone);
                    if (!"14".equals(ftFile.getTnx_type_code().getStringValue()) && "Y".equals(this.getAutoForward())) {
                        isAutoForwarded = FundTransferUtils.autoForwardTheDatesAndSetPostDatedFlag(ftFile, aRunData, true);
                    } else if (!"14".equals(ftFile.getTnx_type_code().getStringValue()) && !FundTransferUtils.validateHolidayCutOffAndSetPostDatedFlag(ftFile, aRunData, true)) {
                        isHolidayCutOffInValid = true;
                    }
                } else if ("BK".equals(tnxFile2.getProduct_code().getStringValue())) {
                    if ("Y".equals(this.getAutoForward())) {
                        final BulkFile bulkFile = (BulkFile) tnxFile2;
                        final GTPCompany bank = GTPSecurity.getCompany(bulkFile.getIssuing_Bank().getAbbv_name().getStringValue());
                        if (bank != null) {
                            userBankTimeZone = bank.getTimeZone();
                        }
                        final Map<String, Object> holidayCutOffConfigMap = (Map<String, Object>) BulkUtils.getHolidayCutOffConfig(bulkFile, aRunData);
                        final BusinessCalendar businessCalendar = (BusinessCalendar) holidayCutOffConfigMap.get("BUSINESS_CALENDAR");
                        final CutOffTime cutOffTime = (CutOffTime) holidayCutOffConfigMap.get("CUTOFF_TIME");
                        final Calendar inputCalendar = (Calendar) holidayCutOffConfigMap.get("INPUT_CALENDAR");
                        final boolean isValid2 = HolidaysAndCutOff.checkCutOffTimeStatus(businessCalendar, cutOffTime, inputCalendar, userBankTimeZone);
                        if (!isValid2) {
                            final Calendar updatedCalendar = HolidaysAndCutOff.getAutoForwardDateForHolidayAndCutOff(businessCalendar, cutOffTime, inputCalendar, userBankTimeZone);
                            bulkFile.getBulk().getValue_date().setValue(new Timestamp(updatedCalendar.getTimeInMillis()));
                            BulkUtils.fixBulkTransactions(bulkFile);
                            isAutoForwarded = true;
                        } else {
                            isAutoForwarded = false;
                        }
                    }
                } else if ("BG".equals(tnxFile2.getProduct_code().getStringValue())) {
                    final BankerGuaranteeFile bgFile = (BankerGuaranteeFile) tnxFile2;
                    if (Utils.isProvisionalApplicable((TransactionProductFile) bgFile) && "Y".equals(bgFile.getBankerGuarantee().getProvisional_status().getValue())) {
                        tnxFile2.getProd_stat_code().setValue("98");
                    } else if ("Y".equals(bgFile.getBankerGuarantee().getLead_bank_flag().getValue()) && !GTPSecurityCheck.hasPermission(aRunData, "bg_lead_bank_access")) {
                        throw new GTPBusinessValidationException("The user does not have lead bank access permission to submit transaction: " + tnxFile2.getRef_id().getValue(), "ERROR_MSG_NO_LEAD_BANK_ACCESS");
                    }
                } else if ("SI".equals(tnxFile2.getProduct_code().getStringValue())) {
                    final StandbyIssuedFile siFile = (StandbyIssuedFile) tnxFile2;
                    if (Utils.isProvisionalApplicable((TransactionProductFile) siFile) && "Y".equals(siFile.getLetterOfCredit().getProvisional_status().getValue())) {
                        tnxFile2.getProd_stat_code().setValue("98");
                    }
                } else if ("LC".equals(tnxFile2.getProduct_code().getStringValue())) {
                    final LetterOfCreditFile lcFile = (LetterOfCreditFile) tnxFile2;
                    if ("Y".equals(lcFile.getLetterOfCredit().getProvisional_status().getValue())) {
                        tnxFile2.getProd_stat_code().setValue("98");
                    }
                } else if ("IO".equals(tnxFile2.getProduct_code().getStringValue())) {
                    final ImportOpenAccountFile ioFile = (ImportOpenAccountFile) tnxFile2;
                    if ("Y".equals(ioFile.getImportOpenAccount().getProvisional_status().getValue())) {
                        tnxFile2.getProd_stat_code().setValue("98");
                    }
                } else if ("EA".equals(tnxFile2.getProduct_code().getStringValue())) {
                    final ExportOpenAccountFile eoFile = (ExportOpenAccountFile) tnxFile2;
                    if ("Y".equals(eoFile.getExportOpenAccount().getProvisional_status().getValue())) {
                        tnxFile2.getProd_stat_code().setValue("98");
                    }
                } else if ("TF".equals(tnxFile2.getProduct_code().getStringValue())) {
                    final FinancingRequestFile tfFile = (FinancingRequestFile) tnxFile2;
                    final String tnxTypeCode2 = tfFile.getFinancingRequest().getTnx_type_code().getValue();
                    if ("Y".equals(tfFile.getFinancingRequest().getProvisional_status().getValue()) || ("13".equals(tnxTypeCode2) && ("66".equals(tfFile.getSub_tnx_type_code().getValue()) || "67".equals(tfFile.getSub_tnx_type_code().getValue())))) {
                        tnxFile2.getProd_stat_code().setValue("98");
                    }
                }
                if (isHolidayCutOffInValid || isPABNotAllowed) {
                    this.rejectedFiles.put(tnxFile2, authorised);
                    newReference.add(1, "E");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "E", tnxFileList.size() == 1);
                } else {
                    tnxFile2.saveFile(authorised, list.get(j, 2), "SUBMIT", "UNSIGNED", tnxFile2.getTnx_type_code().getValue(), user);
                    if (authorised.isAuthorised()) {
                        this.acceptedFiles.put(tnxFile2, authorised);
                        newReference.add(1, "A");
                        Audit.updateContext(aRunData, (Auditable) tnxFile2, "A", tnxFileList.size() == 1);
                    } else {
                        this.rejectedFiles.put(tnxFile2, authorised);
                        newReference.add(1, "F");
                        Audit.updateContext(aRunData, (Auditable) tnxFile2, "F", tnxFileList.size() == 1);
                    }
                    if (isAutoForwarded) {
                        this.autoForwardedFiles.put(tnxFile2, authorised);
                    }
                }
                allFiles.add(tnxFile2);
            } catch (GTPBusinessValidationException bve) {
                if (tnxFile2 != null) {
                    newReference = new Vector();
                    newReference.add(0, tnxFile2.getRef_id());
                    newReference.add(1, "BE");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "BE", tnxFileList.size() == 1);
                    this.businessValidationRejectFiles.put(tnxFile2, bve);
                }
            } catch (GTPException gtpe) {
                if (tnxFile2 != null) {
                    newReference = new Vector();
                    newReference.add(0, tnxFile2.getRef_id());
                    newReference.add(1, "E");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "E", tnxFileList.size() == 1);
                    if (list.get(j, 0).equals(tnxFile2.getRef_id().getValue()) && list.get(j, 1).equals(tnxFile2.getTnx_id().getValue())) {
                        this.failedFiles.put(tnxFile2, authorised);
                    }
                }
                ListDef.LOG.error((Object) gtpe.getMessage(), (Throwable) gtpe);
            } catch (Exception e2) {
                if (tnxFile2 != null) {
                    newReference = new Vector();
                    newReference.add(0, tnxFile2.getRef_id());
                    newReference.add(1, "E");
                    Audit.updateContext(aRunData, (Auditable) tnxFile2, "E", tnxFileList.size() == 1);
                    if (list.get(j, 0).equals(tnxFile2.getRef_id().getValue()) && list.get(j, 1).equals(tnxFile2.getTnx_id().getValue())) {
                        this.failedFiles.put(tnxFile2, authorised);
                    }
                }
                ListDef.LOG.error((Object) e2.getMessage(), (Throwable) e2);
            }
        }
        if (!allFiles.isEmpty()) {
            aRunData.getTemplateInfo().setTemp("__interface_data_source", (Object) allFiles);
        }
        if (!this.acceptedFiles.isEmpty() && this.rejectedFiles.isEmpty() && this.failedFiles.isEmpty() && this.businessValidationRejectFiles.isEmpty()) {
            Audit.audit(aRunData, "A");
        } else if (this.acceptedFiles.isEmpty() && !this.rejectedFiles.isEmpty() && this.failedFiles.isEmpty() && this.businessValidationRejectFiles.isEmpty()) {
            Audit.audit(aRunData, "F");
        } else if (this.acceptedFiles.isEmpty() && this.rejectedFiles.isEmpty() && !this.failedFiles.isEmpty() && this.businessValidationRejectFiles.isEmpty()) {
            Audit.audit(aRunData, "E");
        } else if (this.acceptedFiles.isEmpty() && this.rejectedFiles.isEmpty() && this.failedFiles.isEmpty() && !this.businessValidationRejectFiles.isEmpty()) {
            Audit.audit(aRunData, "BE");
        } else {
            Audit.audit(aRunData, "M");
        }
        Audit.saveContext(aRunData);
        profiler.stop((Class) this.getClass(), "severalSubmitAction");
    }

    @Override
    public String toXML() {
        XML node = new XML("listdef");

        XML candidatesNode = new XML("candidates");
        node.addElement(candidatesNode);
        for (Iterator ite = this.candidates.iterator(); ite.hasNext();) {
            candidatesNode.addElement(((Candidate) ite.next()).toXML());
        }
        XML columnsNode = new XML("columns");
        node.addElement(columnsNode);
        for (Iterator ite = getColumnsIterator(); ite.hasNext();) {
            columnsNode.addElement(((Column) ite.next()).toXML());
        }
        XML parametersNode = new XML("parameters");
        node.addElement(parametersNode);
        for (Iterator ite = this.parameters.values().iterator(); ite.hasNext();) {
            parametersNode.addElement(((Parameter) ite.next()).toXML());
        }
        XML filtersNode = new XML("filters");
        node.addElement(filtersNode);
        for (Iterator ite = this.filters.iterator(); ite.hasNext();) {
            filtersNode.addElement(((Filter) ite.next()).toXML());
        }
        XML aggregatsNode = new XML("aggregates");
        node.addElement(aggregatsNode);
        for (Iterator ite = this.aggregates.iterator(); ite.hasNext();) {
            aggregatsNode.addElement(((Aggregate) ite.next()).toXML());
        }
        node.addElement(this.rootGroup.toXML());

        return node.toString();
    }

    private void updateBatchesContext() {
        Logger.debug("Batch mode management");
        Profiler.inst().start((Class) this.getClass(), "updateBatchContext");
        if (this.hasBatches()) {
            for (final Batch theBatch : this.batches) {
                if (theBatch.getType().equalsIgnoreCase("submit") || theBatch.getType().equalsIgnoreCase("resubmit")) {
                    final GTPUser user = (GTPUser) this.runData.getUser();
                    if (DefaultResourceProvider.CUSTOMER_COUNTERPARTY_TYPE_LIST.contains(user.getCompanyType())) {
                        theBatch.setHasPermission(GTPSecurityCheck.hasPermission(((GTPRunData) this.runData).getUserAcl(), "batch_submission", ((GTPUser) this.runData.getUser()).getEntities()));
                    } else if ("01".equals(user.getCompanyType()) || "02".equals(user.getCompanyType())) {
                        theBatch.setHasPermission(GTPSecurityCheck.hasPermission(((GTPRunData) this.runData).getUserAcl(), "tradeadmin_batch_submission", ((GTPUser) this.runData.getUser()).getEntities()));
                    }
                } else if (theBatch.getType().equalsIgnoreCase("delete")) {
                    theBatch.setHasPermission(true);
                } else if (theBatch.getType().equalsIgnoreCase("cancel")) {
                    theBatch.setHasPermission(true);
                } else if (theBatch.getType().equalsIgnoreCase("cancelDisabled")) {
                    theBatch.setHasPermission(true);
                } else if (theBatch.getType().equalsIgnoreCase("process")) {
                    theBatch.setHasPermission(true);
                } else if (theBatch.getType().equalsIgnoreCase("forward")) {
                    theBatch.setHasPermission(GTPSecurityCheck.hasPermission(((GTPRunData) this.runData).getUserAcl(), "tradeadmin_forward_access", ((GTPUser) this.runData.getUser()).getEntities()));
                } else if (theBatch.getType().equalsIgnoreCase("print")) {
                    theBatch.setHasPermission(GTPSecurityCheck.hasPermission(((GTPRunData) this.runData).getUserAcl(), "batch_print_report", ((GTPUser) this.runData.getUser()).getEntities()));
                } else if (theBatch.getType().equalsIgnoreCase("zip")) {
                    theBatch.setHasPermission(true);
                } else if (theBatch.getType().equalsIgnoreCase("group")) {
                    theBatch.setHasPermission(true);
                } else if (theBatch.getPermission() != null) {
                    theBatch.setHasPermission(GTPSecurityCheck.hasPermission(((GTPRunData) this.runData).getUserAcl(), theBatch.getPermission(), ((GTPUser) this.runData.getUser()).getEntities()));
                } else {
                    theBatch.setHasPermission(false);
                }
                if (theBatch.hasPermission()) {
                    this.listKeys = this.runData.getParameters().getString("list_keys");
                }
                if (theBatch.hasPermission() && this.listKeys != null && !"".equals(this.listKeys)) {
                    this.nbRecordsDeleted = 0;
                    this.acceptedFiles.clear();
                    this.rejectedFiles.clear();
                    this.failedFiles.clear();
                    this.nbRecordsZipped = 0;
                    if (theBatch.getType().equalsIgnoreCase("delete")) {
                        this.severalDeleteAction(this.runData);
                    } else if (theBatch.getType().equalsIgnoreCase("cancel")) {
                        this.severalCancelAction(this.runData);
                    } else if (theBatch.getType().equalsIgnoreCase("submit")) {
                        this.severalSubmitAction(this.runData);
                    } else if (theBatch.getType().equalsIgnoreCase("group")) {
                        this.severalGroupAction(this.runData);
                    } else {
                        if (!theBatch.getType().equalsIgnoreCase("batch_operation")) {
                            continue;
                        }
                        this.severalBatchAction(this.runData);
                    }
                }
            }
        }
        Profiler.inst().stop((Class) this.getClass(), "updateBatchContext");
    }

    private void updateCandidatesContext() throws DOMException {
        Logger.debug("Update candidates");
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "updateCandidatesContext");
        for (final Node node : this.candidateNodes) {
            final String name = node.getAttributes().getNamedItem("name").getNodeValue();
            if (this.parameters.containsKey(name)) {
                final Parameter parameter = (Parameter) this.parameters.get(name);
                final String value = parameter.getValue();
                if (value.equals(parameter.getDefaultValue()) && parameter instanceof ConstrainedParameter) {
                    final String[] allowedValues = ((ConstrainedParameter) parameter).getAllowedValues();
                    for (int i = 1; i < allowedValues.length; ++i) {
                        final Candidate newCandidate = CandidateFactory.newCandidate(allowedValues[i]);
                        if (newCandidate != null) {
                            newCandidate.fromNode(node);
                            newCandidate.updateExecutionContext(this.runData, this);
                            this.candidates.add(newCandidate);
                        }
                    }
                } else {
                    final Candidate newCandidate2 = CandidateFactory.newCandidate(value);
                    if (newCandidate2 == null || !newCandidate2.hasPermission(this.runData)) {
                        continue;
                    }
                    newCandidate2.fromNode(node);
                    newCandidate2.updateExecutionContext(this.runData, this);
                    this.candidates.add(newCandidate2);
                }
            } else {
                final Candidate newCandidate3 = CandidateFactory.newCandidate(name);
                if (newCandidate3 == null || !newCandidate3.hasPermission(this.runData)) {
                    continue;
                }
                newCandidate3.fromNode(node);
                newCandidate3.updateExecutionContext(this.runData, this);
                this.candidates.add(newCandidate3);
            }
        }
        profiler.stop((Class) this.getClass(), "updateCandidatesContext");
    }

    private void updateColumnContext() {
        Logger.debug("Update columns.");
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "updateColumnContext");
        for (final Column aColumn : this.columns) {
            aColumn.setIndex(this.columnIndex);
            aColumn.setSortIndex(this.rawIndex);
            if (ListDef.entityNamesSet.contains(aColumn.getName()) && GTPSecurity.isCustomer(this.runData) && !((GTPUser) this.runData.getUser()).hasEntities()) {
                aColumn.hidden = true;
                Logger.debug("\tEntity column found but user has no entity, hide column.");
            } else if (ListDef.entityNamesSet.contains(aColumn.getName()) && GTPSecurity.isCounterparty(this.runData)) {
                aColumn.hidden = true;
                Logger.debug("\tEntity column found but not applicable for counterparty, hide column.");
            }
            if (aColumn.getName().indexOf("pre_approved_status") != -1 && !DefaultResourceProvider.NON_PAB_ALLOWED) {
                aColumn.hidden = true;
                Logger.debug("\tPAB column found but non-PAB accounts not allowed, hide column.");
            }
            if (!aColumn.hidden) {
                ++this.columnIndex;
            }
            ++this.rawIndex;
        }
        profiler.stop((Class) this.getClass(), "updateColumnContext");
    }

    private void updateCompanyContext() {
        Logger.debug("Update current company");
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "updateCompanyContext");
        try {
            final GTPUser user = (GTPUser) this.runData.getUser();
            this.company = GTPSecurity.getCompany(user.getCompany(), user.getCompanyType());
        } catch (Exception e) {
            ListDef.LOG.error((Object) e.getMessage(), (Throwable) e);
        }
        profiler.stop((Class) this.getClass(), "updateCompanyContext");
    }

    private void updateExecutionContext() {
        try {
            this.updateCompanyContext();
            this.updateParametersContext();
            this.updateCandidatesContext();
            this.updateOrderContext();
            this.updatePaginationContext();
            this.updateBatchesContext();
            this.updateColumnContext();
        } catch (NumberFormatException e) {
            Logger.error("Failure during update of the execution context.", e);
        } catch (DOMException e2) {
            Logger.error("Failure during update of the execution context.", e2);
        }
    }

    private void updateOrderContext() throws NumberFormatException {
        Logger.debug("Update ordering management");
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "updateOrderContext");
        if (this.ordered) {
            final String orderColumn = this.runData.getParameters().getString("order_col");
            if (orderColumn != null && !"".equals(orderColumn)) {
                this.orderIndex = Integer.parseInt(orderColumn);
                this.orderType = this.runData.getParameters().getString("order_type");
                Logger.note("\tInteractive ordering on column n\ufffd " + this.orderIndex);
            } else {
                this.orderIndex = 0;
            }
        }
        profiler.stop((Class) this.getClass(), "updateOrderContext");
    }

    private void updatePaginationContext() throws NumberFormatException {
        Logger.debug("Update pagination management");
        final Profiler profiler = Profiler.inst();
        profiler.start((Class) this.getClass(), "updatePaginationContext");
        if (this.pageSize != 0) {
            final String startOffset = this.runData.getParameters().getString("page");
            if (startOffset != null && !"".equals(startOffset)) {
                this.pageOffset = Integer.parseInt(startOffset);
            } else {
                this.pageOffset = 1;
            }
        }
        if (this.isExported()) {
            if ("Y".equals(this.runData.getParameters().getString("reportschedule"))) {
                final String numberOfRec = this.runData.getParameters().getString("numberOfRec");
                this.pageSize = Integer.valueOf(numberOfRec);
            } else {
                this.pageSize = ListDef.conf.getInt("gtp.list.export.maxrecords", 0);
            }
        }
        profiler.stop((Class) this.getClass(), "updatePaginationContext");
    }

    private void updateParametersContext() {
        Logger.debug("Update Parameters");

        Profiler profiler = Profiler.inst();
        profiler.start(getClass(), "updateParametersContext");
        if (this.runData != null) {
            this.huge = this.hugeMode;
            for (Iterator ite = this.parameters.values().iterator(); ite.hasNext();) {
                Parameter parameter = (Parameter) ite.next();
                parameter.updateExecutionContext(this.runData, this);
                if ((this.hugeMode) && (parameter.isEmpty(this.runData))) {
                    this.huge = false;
                }
            }
            if ((this.viewerType != null) && (this.viewerType.length() > 0)) {
                this.huge = ((this.huge) || ((!ViewerFactory.newViewer(this.viewerType).isOutput()) && (!isExported())));
            } else {
                this.huge = false;
            }
        }
        profiler.stop(getClass(), "updateParametersContext");
    }
}
