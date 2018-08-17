package com.example.firstdemoswaggerjdbc.controller;

import com.example.firstdemoswaggerjdbc.service.ZwService;
import com.example.firstdemoswaggerjdbc.table.Zhangwu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 使用读取配置文件的方式获得JDBC的连接
 * first——demo——swagger——jdbc没有采用此方法，相当于改进
 * 先使用到这里
 */
@RestController
@RequestMapping("Mysql")
@Api(value = "测试Mysql")
public class ZwController {
    @Autowired
    ZwService zwService;

    /**
     * 添加账务
     * @param flname
     * @param money
     * @param zhanghu
     * @param createtime
     * @param description
     * @return
     */
    @RequestMapping(value ="/addData，return all", method= RequestMethod.GET)
    @ApiOperation(value="输入数据并可以进行添加数据", notes="输入数据")
    @ApiImplicitParams({
    })
    public List<Zhangwu> addData(@RequestParam(value = "flname") String flname,
                                 @RequestParam(value = "money") double money,
                                 @RequestParam(value = "zhanghu")String zhanghu,
                                 @RequestParam(value = "createtime") String createtime,
                                 @RequestParam(value = "description")String description){

        return zwService.add(flname,money,zhanghu,createtime,description);

    }

    @RequestMapping(value ="/addData，return one", method= RequestMethod.GET)
    @ApiOperation(value="输入数据并可以进行添加数据", notes="输入数据")
    @ApiImplicitParams({
    })
    public String addData2(@RequestParam(value = "flname") String flname,
                                 @RequestParam(value = "money") double money,
                                 @RequestParam(value = "zhanghu")String zhanghu,
                                 @RequestParam(value = "createtime") String createtime,
                                 @RequestParam(value = "description")String description){

        return zwService.add2(flname,money,zhanghu,createtime,description);

    }

    /**
     * 编辑事务
     * @param zwid
     * @param flname
     * @param money
     * @param zhanghu
     * @param createtime
     * @param description
     * @return
     */
    @RequestMapping(value ="/editData from zwid，return message", method= RequestMethod.GET)
    @ApiOperation(value="输入账务信息", notes="输入账务全部信息")
    @ApiImplicitParams({
    })
    public String edit(@RequestParam(value = "zwid") int zwid,
                       @RequestParam(value = "flname") String flname,
                       @RequestParam(value = "money") double money,
                       @RequestParam(value = "zhanghu")String zhanghu,
                       @RequestParam(value = "createtime") String createtime,
                       @RequestParam(value = "description")String description){

        //id为主键，先找到相应的记录，然后根据后边的属性编辑
        return zwService.edit(zwid,flname,money,zhanghu,createtime,description);
    }

    /**
     * 删除账务
     * @param zwid
     * @return
     */
    @RequestMapping(value ="/deleteData from zwid，return message", method= RequestMethod.GET)
    @ApiOperation(value="输入账务ID", notes="输入zwid")
    @ApiImplicitParams({
    })
    public String delete(@RequestParam(value = "zwid") int zwid){

        return zwService.delete(zwid);
    }

    /**
     * 查询账务
     * @param flname
     * @return
     */
    @RequestMapping(value ="/searchData from flname，return list", method= RequestMethod.GET)
    @ApiOperation(value="查询特定名称的事务", notes="查询特定事务")
    @ApiImplicitParams({
    })
    public List<Zhangwu> searchOfName(@RequestParam(value = "flname") String flname){

        return zwService.searchOfName(flname);
    }


    @RequestMapping(value ="/searchData from zwid，return Object", method= RequestMethod.GET)
    @ApiOperation(value="查询特定名称的事务", notes="查询特定事务")
    @ApiImplicitParams({
    })
    public Zhangwu searchOfZwid(@RequestParam(value = "zwid") int zwid){

        return zwService.searchOfZwid(zwid);
    }

    @RequestMapping(value ="/searchData from createtime，return Object", method= RequestMethod.GET)
    @ApiOperation(value="查询特定名称的事务", notes="查询特定事务")
    @ApiImplicitParams({
    })
    public List<Zhangwu> searchOfTime(@RequestParam(value = "createtime") String createtime){

        return zwService.searchOfTime(createtime);
    }



    @RequestMapping(value ="/searchData，return all", method= RequestMethod.GET)
    @ApiOperation(value="查询所有事务", notes="查询事务")
    @ApiImplicitParams({
    })
    public List<Zhangwu> searchAll(){

        return zwService.searchAll();
    }




}
