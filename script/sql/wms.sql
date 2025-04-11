-- ----------------------------
-- 1、基础资料 - SKU分类表
-- ----------------------------
create table bas_category (
    category_id     bigint(20)      not null                   comment '分类ID',
    parent_id       bigint(20)      default 0                  comment '父分类ID',
    ancestors       varchar(500)    default ''                 comment '祖级列表',
    category_name   varchar(50)     not null                   comment '分类名称',
    category_code   varchar(64)     default null               comment '分类编码',
    order_num       int(4)          default 0                  comment '显示顺序',
    level           int             default 1                   comment '层级',
    status          char(1)         default '0'                comment '状态（0正常 1停用）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    primary key (category_id)
) engine=innodb comment = 'SKU分类表';

-- ----------------------------
-- 2、基础资料 - 计量单位表
-- ----------------------------
create table bas_unit (
    unit_id         bigint(20)      not null                   comment '单位ID',
    unit_name       varchar(50)     not null                   comment '单位名称',
    unit_code       varchar(64)     default null               comment '单位编码',
    order_num       int(4)          default 0                  comment '显示顺序',
    status          char(1)         default '0'                comment '状态（0正常 1停用）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    primary key (unit_id)
) engine=innodb comment = '计量单位表';

-- ----------------------------
-- 3、基础资料 - SKU信息表
-- ----------------------------
create table bas_sku (
    sku_id          bigint(20)      not null                   comment 'SKU ID',
    category_id     bigint(20)      not null                   comment '分类ID',
    sku_name        varchar(200)    not null                   comment 'SKU名称',
    sku_code        varchar(64)     not null                   comment 'SKU编码',
    bar_code        varchar(64)     default null               comment '条形码',
    unit_id         bigint(20)      default null               comment '基本单位',
    weight          decimal(10,2)   default 0                  comment '重量(KG)',
    volume          decimal(10,2)   default 0                  comment '体积(m³)',
    shelf_life      int             default 0                  comment '保质期(天)',
    min_stock       decimal(10,2)   default 0                  comment '最小库存',
    max_stock       decimal(10,2)   default 0                  comment '最大库存',
    specs           varchar(500)    default null               comment '规格',
    batch_control   char(1)         default '0'                comment '批次管理（0否 1是）',
    status          char(1)         default '0'                comment '状态（0正常 1停用）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    out_strategy_days int DEFAULT 0                            COMMENT '出库策略：过期后容忍出库的天数' ,
    in_strategy_days int DEFAULT 0                             COMMENT '入库策略：生产日期超过多少天不收',
    primary key (sku_id)
) engine=innodb comment = 'SKU信息表';

-- ----------------------------
-- 4、基础资料 - 供应商信息表
-- ----------------------------
create table bas_supplier (
    supplier_id     bigint(20)      not null                   comment '供应商ID',
    supplier_name   varchar(200)    not null                   comment '供应商名称',
    supplier_code   varchar(64)     not null                   comment '供应商编码',
    contact         varchar(50)     default null               comment '联系人',
    phone           varchar(20)     default null               comment '联系电话',
    email          varchar(100)    default null               comment '邮箱',
    address        varchar(500)    default null               comment '地址',
    status         char(1)         default '0'                comment '状态（0正常 1停用）',
    create_dept    bigint(20)      default null               comment '创建部门',
    create_by      bigint(20)      default null               comment '创建者',
    create_time    datetime                                   comment '创建时间',
    update_by      bigint(20)      default null               comment '更新者',
    update_time    datetime                                   comment '更新时间',
    remark         varchar(500)    default null               comment '备注',
    primary key (supplier_id)
) engine=innodb comment = '供应商信息表';

-- ----------------------------
-- 5、库位管理 - 仓库信息表
-- ----------------------------
create table wms_warehouse (
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    warehouse_name  varchar(200)    not null                   comment '仓库名称',
    warehouse_code  varchar(64)     not null                   comment '仓库编码',
    area           decimal(10,2)    default 0                  comment '面积(m²)',
    address        varchar(500)     default null               comment '地址',
    charge_person   bigint(20)      default null               comment '负责人',
    status         char(1)          default '0'                comment '状态（0正常 1停用）',
    create_dept    bigint(20)       default null               comment '创建部门',
    create_by      bigint(20)       default null               comment '创建者',
    create_time    datetime                                    comment '创建时间',
    update_by      bigint(20)       default null               comment '更新者',
    update_time    datetime                                    comment '更新时间',
    remark         varchar(500)     default null               comment '备注',
    primary key (warehouse_id)
) engine=innodb comment = '仓库信息表';

-- ----------------------------
-- 6、库位管理 - 库区信息表
-- ----------------------------
create table wms_area (
    area_id        bigint(20)      not null                   comment '库区ID',
    warehouse_id   bigint(20)      not null                   comment '仓库ID',
    area_name      varchar(200)    not null                   comment '库区名称',
    area_code      varchar(64)     not null                   comment '库区编码',
    status         char(1)         default '0'                comment '状态（0正常 1停用）',
    create_dept    bigint(20)      default null               comment '创建部门',
    create_by      bigint(20)      default null               comment '创建者',
    create_time    datetime                                   comment '创建时间',
    update_by      bigint(20)      default null               comment '更新者',
    update_time    datetime                                   comment '更新时间',
    remark         varchar(500)    default null               comment '备注',
    primary key (area_id)
) engine=innodb comment = '库区信息表';

-- ----------------------------
-- 7、库位管理 - 库位信息表
-- ----------------------------
create table wms_location (
    location_id     bigint(20)      not null                   comment '库位ID',
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    area_id         bigint(20)      not null                   comment '库区ID',
    location_name   varchar(200)    not null                   comment '库位名称',
    location_code   varchar(64)     not null                   comment '库位编码',
    capacity        decimal(10,2)   default 0                  comment '容量',
    status          char(1)         default '0'                comment '状态（0正常 1停用 2占用）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    primary key (location_id)
) engine=innodb comment = '库位信息表';

-- ----------------------------
-- 8、库存管理 - 库存信息表
-- ----------------------------
create table wms_stock (
    stock_id        bigint(20)      not null                   comment '库存ID',
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    area_id         bigint(20)      not null                   comment '库区ID',
    location_id     bigint(20)      not null                   comment '库位ID',
    sku_id          bigint(20)      not null                   comment 'SKU ID',
    stock_quantity  decimal(10,2)   default 0                  comment '库存数量',
    locked_quantity decimal(10,2)   default 0                  comment '锁定数量',
    available_quantity decimal(10,2) default 0                 comment '可用数量',
    status          char(1)         default '0'                comment '状态（0正常 1警告 2异常）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    primary key (stock_id)
) engine=innodb comment = '库存信息表';

-- ----------------------------
-- 9、库存管理 - 库存操作日志表
-- ----------------------------
create table wms_stock_log (
    log_id          bigint(20)      not null                   comment '日志ID',
    stock_id        bigint(20)      not null                   comment '库存ID',
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    location_id     bigint(20)      not null                   comment '库位ID',
    sku_id          bigint(20)      not null                   comment 'SKU ID',
    quantity_before decimal(10,2)   default 0                  comment '操作前数量',
    quantity_after  decimal(10,2)   default 0                  comment '操作后数量',
    quantity_change decimal(10,2)   default 0                  comment '操作数量',
    operate_type    char(1)         not null                   comment '操作类型（1入库 2出库 3盘点 4调拨）',
    operate_id      bigint(20)      not null                   comment '操作单据ID',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (log_id)
) engine=innodb comment = '库存操作日志表';

-- ----------------------------
-- 10、入库管理 - 入库单表
-- ----------------------------
create table wms_in_order (
    order_id        bigint(20)      not null                   comment '入库单ID',
    order_no        varchar(64)     not null                   comment '入库单号',
    order_type      char(1)         not null                   comment '入库类型（1采购入库 2退货入库 3调拨入库）',
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    supplier_id     bigint(20)      default null               comment '供应商ID',
    order_date      datetime        not null                   comment '单据日期',
    status          char(1)         default '0'                comment '单据状态（0草稿 1待审核 2已审核 3已入库）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (order_id)
) engine=innodb comment = '入库单表';

-- ----------------------------
-- 11、入库管理 - 入库单详情表
-- ----------------------------
create table wms_in_order_detail (
    detail_id       bigint(20)      not null                   comment '详情ID',
    order_id        bigint(20)      not null                   comment '入库单ID',
    sku_id          bigint(20)      not null                   comment 'SKU ID',
    plan_quantity   decimal(10,2)   not null                   comment '计划数量',
    real_quantity   decimal(10,2)   default 0                  comment '实际数量',
    location_id     bigint(20)      default null               comment '库位ID',
    batch_no        varchar(64)     default null               comment '批次号',
    produce_date    datetime        default null               comment '生产日期',
    expire_date     datetime        default null               comment '有效期',
    supplier_id     bigint(20)      default null               comment '供应商ID',
    status          char(1)         default '0'                comment '状态（0未入库 1已入库）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (detail_id)
) engine=innodb comment = '入库单详情表';

-- ----------------------------
-- 12、出库管理 - 出库单表
-- ----------------------------
create table wms_out_order (
    order_id        bigint(20)      not null                   comment '出库单ID',
    order_no        varchar(64)     not null                   comment '出库单号',
    order_type      char(1)         not null                   comment '出库类型（1销售出库 2退货出库 3调拨出库）',
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    customer_id     bigint(20)      default null               comment '客户ID',
    order_date      datetime        not null                   comment '单据日期',
    status          char(1)         default '0'                comment '单据状态（0草稿 1待审核 2已审核 3已出库）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (order_id)
) engine=innodb comment = '出库单表';

-- ----------------------------
-- 13、出库管理 - 出库单详情表
-- ----------------------------
create table wms_out_order_detail (
    detail_id       bigint(20)      not null                   comment '详情ID',
    order_id        bigint(20)      not null                   comment '出库单ID',
    sku_id          bigint(20)      not null                   comment 'SKU ID',
    plan_quantity   decimal(10,2)   not null                   comment '计划数量',
    real_quantity   decimal(10,2)   default 0                  comment '实际数量',
    location_id     bigint(20)      default null               comment '库位ID',
    batch_no        varchar(64)     default null               comment '批次号',
    produce_date    datetime        default null               comment '生产日期',
    expire_date     datetime        default null               comment '有效期',
    supplier_id     bigint(20)      default null               comment '供应商ID',
    status          char(1)         default '0'                comment '状态（0未出库 1已出库）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (detail_id)
) engine=innodb comment = '出库单详情表';

-- ----------------------------
-- 14、盘点管理 - 盘点单表
-- ----------------------------
create table wms_check_order (
    order_id        bigint(20)      not null                   comment '盘点单ID',
    order_no        varchar(64)     not null                   comment '盘点单号',
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    area_id         bigint(20)      default null               comment '库区ID',
    location_id     bigint(20)      default null               comment '库位ID',
    check_date      datetime        not null                   comment '盘点日期',
    status          char(1)         default '0'                comment '单据状态（0草稿 1待审核 2已审核 3完成）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (order_id)
) engine=innodb comment = '盘点单表';

-- ----------------------------
-- 15、盘点管理 - 盘点单详情表
-- ----------------------------
create table wms_check_detail (
    detail_id       bigint(20)      not null                   comment '详情ID',
    order_id        bigint(20)      not null                   comment '盘点单ID',
    sku_id          bigint(20)      not null                   comment 'SKU ID',
    location_id     bigint(20)      not null                   comment '库位ID',
    stock_quantity  decimal(10,2)   not null                   comment '账面数量',
    check_quantity  decimal(10,2)   default 0                  comment '盘点数量',
    diff_quantity   decimal(10,2)   default 0                  comment '差异数量',
    batch_no        varchar(64)     default null               comment '批次号',
    produce_date    datetime        default null               comment '生产日期',
    expire_date     datetime        default null               comment '有效期',
    supplier_id     bigint(20)      default null               comment '供应商ID',
    status          char(1)         default '0'                comment '状态（0未盘点 1已盘点）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (detail_id)
) engine=innodb comment = '盘点单详情表';

-- 新增批次库存表
create table wms_stock_batch (
    batch_id        bigint(20)      not null                   comment '批次库存ID',
    stock_id        bigint(20)      not null                   comment '库存ID',
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    area_id         bigint(20)      not null                   comment '库区ID',
    location_id     bigint(20)      not null                   comment '库位ID',
    sku_id          bigint(20)      not null                   comment 'SKU ID',
    batch_no        varchar(64)     not null                   comment '批次号',
    batch_quantity  decimal(10,2)   default 0                  comment '批次库存数量',
    locked_quantity decimal(10,2)   default 0                  comment '锁定数量',
    available_quantity decimal(10,2) default 0                 comment '可用数量',
    supplier_id     bigint(20)      default null               comment '供应商ID',
    produce_date    datetime        default null               comment '生产日期',
    expire_date     datetime        default null               comment '有效期',
    status          char(1)         default '0'                comment '状态（0正常 1警告 2异常）',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    primary key (batch_id)
) engine=innodb comment = '批次库存表';

-- 新增批次操作日志表
create table wms_stock_batch_log (
    log_id          bigint(20)      not null                   comment '日志ID',
    batch_id        bigint(20)      not null                   comment '批次库存ID',
    stock_id        bigint(20)      not null                   comment '库存ID',
    warehouse_id    bigint(20)      not null                   comment '仓库ID',
    location_id     bigint(20)      not null                   comment '库位ID',
    sku_id          bigint(20)      not null                   comment 'SKU ID',
    batch_no        varchar(64)     not null                   comment '批次号',
    quantity_before decimal(10,2)   default 0                  comment '操作前数量',
    quantity_after  decimal(10,2)   default 0                  comment '操作后数量',
    quantity_change decimal(10,2)   default 0                  comment '操作数量',
    operate_type    char(1)         not null                   comment '操作类型（1入库 2出库 3盘点 4调拨）',
    operate_id      bigint(20)      not null                   comment '操作单据ID',
    create_dept     bigint(20)      default null               comment '创建部门',
    create_by       bigint(20)      default null               comment '创建者',
    create_time     datetime                                   comment '创建时间',
    update_by       bigint(20)      default null               comment '更新者',
    update_time     datetime                                   comment '更新时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (log_id)
) engine=innodb comment = '批次库存操作日志表';

-- ----------------------------
-- 16、单据操作日志表 - 记录所有单据的操作历史
-- ----------------------------
create table wms_order_log (
    log_id          bigint(20)      not null                   comment '日志ID',
    order_id        bigint(20)      not null                   comment '单据ID',
    order_type      char(1)         not null                   comment '单据类型（1入库单 2出库单 3盘点单 4调拨单）',
    order_no        varchar(64)     not null                   comment '单据编号',
    operate_type    char(1)         not null                   comment '操作类型（1创建 2修改 3审核 4撤销 5删除 6确认）',
    status_before   char(1)         default null               comment '操作前状态',
    status_after    char(1)         default null               comment '操作后状态',
    operate_content varchar(1000)   default ''                 comment '操作内容',
    create_dept     bigint(20)      default null               comment '操作部门',
    create_by       bigint(20)      default null               comment '操作人ID',
    create_name     varchar(50)     default null               comment '操作人名称',
    create_time     datetime                                   comment '操作时间',
    remark          varchar(500)    default null               comment '备注',
    del_flag        char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
    primary key (log_id)
) engine=innodb comment = '单据操作日志表';

-- ----------------------------
-- 17、SKU信息历史变更表 - 记录sku的历史变更 由于创建又创建人和删除的时候有最后删除有更新人，只记录修改了那些值
-- ----------------------------
create table bas_sku_history_log (
     log_id          bigint(20)      not null                   comment '日志ID',
     sku_id          bigint(20)      not null                   comment 'SKU ID',
     snapshot        text            default null               comment 'SKU数据快照(JSON格式)',
     operate_type    char(1)         not null                   comment '操作类型（1新增 2修改 3删除）',
     operate_time    datetime        not null                   comment '操作时间',
     operate_by      bigint(20)      not null                   comment '操作人ID',
     operate_name    varchar(50)     default null               comment '操作人姓名',
     operate_dept    bigint(20)      default null               comment '操作部门',
     remark          varchar(500)    default null               comment '备注',
     primary key (log_id),
     index idx_sku_id (sku_id)
) engine=innodb comment = 'SKU信息历史变更表';
