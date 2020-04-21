package cn.example.sacw.mapper;

import cn.example.sacw.model.Ssdt;
import cn.example.sacw.model.ZzwInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


/**
 * 物品统计的mapper
 * @author Administrator
 */
@Mapper
@Repository
public interface ZzwMapper extends BaseMapper<ZzwInfo> {


    /**
     * 统计当前库存数
     * @return
     */
    @Select("select count(wpbh) from zzw_info  where wpstatus = '5' and isdelete = 0 ")
    public int getKc();

    /**
     * 统计当月入库数
     * @return
     */
    @Select("select count(wpbh) from zzw_info  where wpstatus = '5' and to_char(wprksj,'mm')=to_char(sysdate,'mm') and isdelete = 0 ")
    public int getMonthRk();

    /**
     * 统计当月出库数
     * @return
     */
    @Select("select count(wpbh) from zzw_info  where wpstatus = '13' and to_char(wpcksj,'mm')=to_char(sysdate,'mm') and isdelete = 0 ")
    public int getMonthCk();

    /**
     * 统计当月移交数
     * @return
     */
    @Select("select count(wpbh) from zzw_info  where wpstatus = '8' and to_char(wpyjsj,'mm')=to_char(sysdate,'mm') and isdelete = 0 ")
    public int getMonthYj();


    /**
     * 统计往前十天的数据
     * @param kssj
     * @param jssj
     * @return
     */
    @Select("select sum(case when z.wpcksj between to_date(#{kssj},'yyyy-mm-dd hh24:mi:ss') and to_date(#{jssj},'yyyy-mm-dd hh24:mi:ss') and z.wpstatus = '13' then 1 else 0 end) as ck, sum(case when z.wprksj between to_date(#{kssj},'yyyy-mm-dd hh24:mi:ss') and to_date(#{jssj},'yyyy-mm-dd hh24:mi:ss') and z.wpstatus = '5' then 1 else 0 end) as rk from zzw_info z where z.isdelete = 0 ")
    public Map<Object,Object> getCrk(@Param("kssj") String kssj, @Param("jssj") String jssj);

    /**
     * 根据案件类型统计的
     * @param ajlx
     * @return
     */
    @Select("select t.typename as ajlb,count(a.ga_ah) as count from aj_info a right join (select * from t_s_type where typegroupid = #{ajlx}) t on a.ajlb = t.typecode group by t.typename")
    @MapKey("ajlb")
    public List<Map<Object,Object>> getAjlx(@Param("ajlx") String ajlx);

    /**
     * 根据物品的二级分类来统计的
     * @param wplx
     * @return
     */
    @Select("select t.typename as wplx,count(wpbh) as count from zzw_info z right join (select * from t_s_type where typegroupid = #{wplx} ) t on z.ejfl = t.typecode and z.isdelete = 0 group by t.typename ")
    @MapKey("wplx")
    public List<Map<Object,Object>> getWplx(@Param("wplx") String wplx);


    /**
     * 统计出库方式
     * @param ckfs
     * @return
     */
    @Select("select t.typename as cklx , count(w.wpbh) as count from wpck_info w right join (select * from t_s_type where typegroupid = #{ckfs} ) t on w.ckyy = t.typecode  group by t.typename")
    public List<Map<Object,Object>> getCkfs(@Param("ckfs") String ckfs);


    /**
     * 根据操作类型统计
     * @param czlx
     * @return
     */
    @Select("select * from (\n" +
            "select w.lrrq as lrrq," +
            "(select typename from t_s_type where typegroupid = #{czlx} and typecode = w.cllb) as czlx," +
            "z.wpmc as wpmc,z.wpsl as wpsl,a.gaaj_name as ajmc " +
            "from wpcl w ,zzw_info z ,aj_info a " +
            "where w.wpbh = z.wpbh and z.ajbh = a.ga_ah  and w.lrrq is not null order by lrrq desc)  where rownum < 11")
    public List<Ssdt> getSsdt(@Param("czlx") String czlx);


    /**
     * 根据物品的存放方式统计
     * @return
     */
    @Select("select  '1个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -2), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -1), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '2个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -3), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -2), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '3个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -4), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -3), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '4个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -5), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -4), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '5个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -6), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -5), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '6个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -7), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -6), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '7个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -8), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -7), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '8个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -9), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -8), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '9个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -10), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -9), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '10个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -11), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -10), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '11个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -12), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -11), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0\n" +
            "union all\n" +
            "select  '12个月' as month,count(wpbh) as count from zzw_info where wpstatus = '5' and  wprksj is not null and  wprksj between to_date(to_char(add_months(sysdate, -13), 'yyyy-mm-dd'),'yyyy-mm-dd') and  to_date(to_char(add_months(sysdate, -12), 'yyyy-mm-dd'),'yyyy-mm-dd') and isdelete = 0")
    public List<Map<Object,Object>> getCfsj();


    /**
     * 统计物品超期未入库的数据
     * @param ajlx
     * @return
     */
    @Select("select t.typename as ajlx , count(s.ajlb) as count from t_s_type t left join (select a.ajlb from aj_info a inner join (select z.ajbh from wpyjjl w , zzw_info z where w.wpbh = z.wpbh and w.yjlx = '1' and w.iscl = '未处理') w on a.ga_ah = w.ajbh) s on t.typecode = s.ajlb where t.typegroupid = #{ajlx} group by t.typename ")
    public List<Map<Object,Object>> getCqwrk(@Param("ajlx") String ajlx);


    /**
     * 这里统计的是案结物未清的数据
     * @param ajlx   案件类型的码表id
     * @return
     */
    @Select("select t.typename as ajlx , count(s.ajlb) as count from t_s_type t left join (select a.ajlb from aj_info a inner join (select z.ajbh from wpyjjl w , zzw_info z where w.wpbh = z.wpbh and w.yjlx = '4' and w.iscl = '未处理') w on a.ga_ah = w.ajbh) s on t.typecode = s.ajlb where t.typegroupid = #{ajlx} group by t.typename")
    public List<Map<Object,Object>> getCqwck(@Param("ajlx") String ajlx);


}
