package cn.example.sacw.service;

import cn.example.sacw.model.Ssdt;

import java.util.List;
import java.util.Map;

/**
 * 物品的service
 * @author Administrator
 */
public interface ZzwService {


    /**
     * 得到页面左上角的几个数
     * @return
     */
    public Map<String,Integer> getNum();

    /**
     * 统计出入库的数据
     * @return
     */
    public Map<Object,Map<Object,Object>> getCrk();

    /**
     * 统计物品类型的数据
     * @return
     */
    public List<Map<Object,Object>> getWplx();

    /**
     * 统计出库方式的数据
     * @return
     */
    public List<Map<Object,Object>> getCkfs();

    /**
     * 统计案件类型的数据
     * @return
     */
    public List<Map<Object,Object>> getAjlx();

    /**
     * 统计实时动态的数据
     * @return
     */
    public List<Ssdt> getSsdt();

    /**
     * 统计存放时间的数据
     * @return
     */
    public List<Map<Object,Object>> getCfsj();

    /**
     * 统计超期未入库的数据
     * @return
     */
    public List<Map<Object,Object>> getCqwrk();

    /**
     * 统计超期未出库的数据
     * @return
     */
    public List<Map<Object,Object>> getCqwck();
}
