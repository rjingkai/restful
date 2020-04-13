package cn.example.sacw.service.impl;

import cn.example.sacw.mapper.ZzwMapper;
import cn.example.sacw.model.Ssdt;
import cn.example.sacw.service.ZzwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ZzwServiceImpl implements ZzwService {


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
        return zzwMapper.getCkfs();
    }

    @Override
    public List<Map<Object, Object>> getAjlx() {
        return zzwMapper.getAjlx();
    }

    @Override
    public List<Ssdt> getSsdt() {
        return zzwMapper.getSsdt();
    }

    @Override
    public Map<Object,Map<Object,Object>> getCrk(){
        LinkedHashMap<Object,Map<Object,Object>> map = new LinkedHashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 9; i >=0; i--) {
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
       return zzwMapper.getWplx();
    }


}
