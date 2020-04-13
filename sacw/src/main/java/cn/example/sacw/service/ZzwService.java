package cn.example.sacw.service;

import cn.example.sacw.model.Ssdt;

import java.util.List;
import java.util.Map;

public interface ZzwService {


    public Map<String,Integer> getNum();

    public Map<Object,Map<Object,Object>> getCrk();

    public List<Map<Object,Object>> getWplx();

    public List<Map<Object,Object>> getCkfs();

    public List<Map<Object,Object>> getAjlx();

    public List<Ssdt> getSsdt();
}
