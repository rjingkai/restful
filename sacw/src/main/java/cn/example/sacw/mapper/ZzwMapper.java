package cn.example.sacw.mapper;

import cn.example.sacw.model.Ssdt;
import cn.example.sacw.model.ZzwInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface ZzwMapper extends BaseMapper<ZzwInfo> {

    @Select("SELECT COUNT(WPBH) FROM ZZW_INFO  WHERE WPSTATUS = '5' AND ISDELETE = 0 ")
    public int getKc();

    @Select("SELECT COUNT(WPBH) FROM ZZW_INFO  WHERE WPSTATUS = '5' AND TO_CHAR(WPRKSJ,'mm')=to_char(sysdate,'mm') AND ISDELETE = 0 ")
    public int getMonthRk();

    @Select("SELECT COUNT(WPBH) FROM ZZW_INFO  WHERE WPSTATUS = '13' AND TO_CHAR(WPCKSJ,'mm')=to_char(sysdate,'mm') AND ISDELETE = 0 ")
    public int getMonthCk();

    @Select("SELECT COUNT(WPBH) FROM ZZW_INFO  WHERE WPSTATUS = '8' AND TO_CHAR(WPYJSJ,'mm')=to_char(sysdate,'mm') AND ISDELETE = 0 ")
    public int getMonthYj();

    @Select("SELECT SUM(CASE WHEN Z.WPCKSJ BETWEEN TO_DATE(#{kssj},'yyyy-MM-dd hh24:mi:ss') AND TO_DATE(#{jssj},'yyyy-MM-dd hh24:mi:ss') AND Z.WPSTATUS = '13' THEN 1 ELSE 0 END) AS ck, SUM(CASE WHEN Z.WPRKSJ BETWEEN TO_DATE(#{kssj},'yyyy-MM-dd hh24:mi:ss') AND TO_DATE(#{jssj},'yyyy-MM-dd hh24:mi:ss') AND Z.WPSTATUS = '5' THEN 1 ELSE 0 END) AS rk FROM ZZW_INFO Z WHERE Z.ISDELETE = 0 ")
    public Map<Object,Object> getCrk(@Param("kssj") String kssj, @Param("jssj") String jssj);

    //根据案件类型（刑事和行政）来查的
    @Select("select t.typename as ajlb,count(a.ga_ah) as count from aj_info a right join (select * from t_s_type where typegroupid = '402881fd6c21f87f016c2261b8ff0007') t on a.ajlb = t.typecode GROUP BY t.typename")
    @MapKey("ajlb")
    public List<Map<Object,Object>> getAjlx();

    //根据物品的二级分类来查的
    @Select("select t.typename as wplx,count(wpbh) as count from zzw_info z right join (select * from t_s_type where typegroupid = '402881186bd03f9d016bd0c3e48f00e3' ) t on z.ejfl = t.typecode and z.isdelete = 0 group by t.typename ")
    @MapKey("wplx")
    public List<Map<Object,Object>> getWplx();


    //出库方式   ----》    handle
    @Select("select t.typename as cklx , count(w.wpbh) as count from wpck_info w right join (select * from t_s_type where typegroupid = '402881186bd03f9d016bd0999a690035' ) t on w.ckyy = t.typecode  group by t.typename")
    public List<Map<Object,Object>> getCkfs();


    @Select("select * from (\n" +
            "select w.lrrq as lrrq," +
            "(select typename from t_s_type where typegroupid = '4028005e6c3cb74b016c3d04b37c0003' and typecode = w.cllb) as czlx," +
            "z.wpmc as wpmc,z.wpsl as wpsl,a.gaaj_name as ajmc " +
            "from wpcl w ,zzw_info z ,aj_info a " +
            "where w.wpbh = z.wpbh and z.ajbh = a.ga_ah  and w.lrrq is not null order by lrrq desc)  where rownum < 11")
    public List<Ssdt> getSsdt();





}
