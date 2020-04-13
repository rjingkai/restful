package cn.example.sacw.controller;


import cn.example.sacw.model.Ssdt;
import cn.example.sacw.service.ZzwService;
import cn.example.sacw.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CosNaming.IstringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/index")
@Api(value = "访问控制器")
public class ZzwController {


    @Autowired
    private ZzwService zzwService;


    @RequestMapping("/getNum")
    @ApiOperation(value = "返回首页左上角的几个数数据")
    public ResponseResult getNum(){
        Map<String,Integer> map = zzwService.getNum();
        return ResponseResult.success(map);
    }


    @RequestMapping("/getCrk")
    @ApiOperation(value = "返回出入库的折线图数据")
    public ResponseResult getCrk(){
        Map<Object,Map<Object,Object>> map = zzwService.getCrk();
        return ResponseResult.success(map);
    }


    @RequestMapping("/getWplx")
    @ApiOperation(value = "返回分类库存里的根据物品类型统计的数据")
    public ResponseResult getWplx(){
        List<Map<Object,Object>> map = zzwService.getWplx();
        return ResponseResult.success(map);
    }


    @RequestMapping("/getCkfs")
    @ApiOperation(value = "返回出库方式饼状图数据" )
    public ResponseResult getCkfs(){
        List<Map<Object,Object>> map = zzwService.getCkfs();
        return ResponseResult.success(map);
    }


    @RequestMapping("/getAjlx")
    @ApiOperation(value = "返回案件类型饼状图的数据")
    public ResponseResult getAjlx(){
        List<Map<Object,Object>> map = zzwService.getAjlx();
        return ResponseResult.success(map);
    }


    @RequestMapping("/getSsdt")
    @ApiOperation(value = "返回实时动态数据")
    public ResponseResult getSsdt(){
        List<Ssdt> data = zzwService.getSsdt();
        return ResponseResult.success(data);
    }







}