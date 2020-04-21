package cn.example.sacw.service.impl;

import cn.example.sacw.mapper.ZzwMapper;
import cn.example.sacw.model.Ssdt;
import cn.example.sacw.service.ZzwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 物品service的实现类
 * @author Administrator
 */
@Service
public class ZzwServiceImpl implements ZzwService {

    /**
     * 案件类型的groupID
     */
    private static final String ajlxgroupid = "402881fd6c21f87f016c2261b8ff0007";

    /**
     * 物品二级分类的groupID
     */
    private static final String wplxgroupid = "402881186bd03f9d016bd0c3e48f00e3";

    /**
     * 出库方式的groupID
     */
    private static final String ckfsgroupid = "402881186bd03f9d016bd0999a690035";

    /**
     * 物品操作类型的groupID
     */
    private static final String czlxgroupid = "4028005e6c3cb74b016c3d04b37c0003";


    @Autowired
    private ZzwMapper zzwMapper;


    @Override
    public Map<String,Integer> getNum(){
        Map<String,Integer> map = new HashMap<>();
        map.put("wpkc",zzwMapper.getKc());
        map.put("dyrk",zzwMapper.getMonthRk());
        map.put("dyck",zzwMapper.getMonthCk());
        map.put("dyyj",zzwMapper.getMonthYj());
        return map;
    }


    @Override
    public List<Map<Object, Object>> getCkfs() {
        return zzwMapper.getCkfs(ckfsgroupid);
    }

    @Override
    public List<Map<Object, Object>> getAjlx() {
        return zzwMapper.getAjlx(ajlxgroupid);
    }

    @Override
    public List<Ssdt> getSsdt() {
        return zzwMapper.getSsdt(czlxgroupid);
    }

    @Override
    public List<Map<Object, Object>> getCfsj() {
        return zzwMapper.getCfsj();
    }

    @Override
    public List<Map<Object, Object>> getCqwrk() {
        return zzwMapper.getCqwrk(ajlxgroupid);
    }

    @Override
    public List<Map<Object, Object>> getCqwck() {
        return zzwMapper.getCqwck(ajlxgroupid);
    }

    @Override
    public Map<Object,Map<Object,Object>> getCrk(){
        LinkedHashMap<Object,Map<Object,Object>> map = new LinkedHashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 9; i >= 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(calendar.DATE, -i);
            String kssj = format.format(calendar.getTime());
            String jssj = format.format(calendar.getTime());
            Map<Object,Object> data = zzwMapper.getCrk(kssj+" 00:00:00",jssj+" 23:59:59");
            if (data!=null){
                map.put(kssj,data);
            }
        }
        return map;
    }

    @Override
    public List<Map<Object,Object>> getWplx(){
       return zzwMapper.getWplx(wplxgroupid);
    }


}
