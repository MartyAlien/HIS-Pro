package com.hrbu.his.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbu.his.bean.Cov19Bean;
import com.hrbu.his.bean.LocationNum;
import com.hrbu.his.bean.SarsBean;
import com.hrbu.his.bean.UnpayVo;
import com.hrbu.his.bean.yi163.AreaTree;
import com.hrbu.his.bean.yi163.Root;
import com.hrbu.his.bean.yi163.Today;
import com.hrbu.his.request.Result;
import com.hrbu.his.utils.GetData2Util;
import com.hrbu.his.utils.GetDataUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: SarsDataServlet
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/16 13:47
 */
@WebServlet("/sars/*")
public class SarsDataServlet extends BaseServlet {
    private static final int TODAY_MODE=1;
    private static final int TOTAL_MODE=0;
    public Result getSarsData(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        // 调用方法将json字符串转换为对应的实例
        ObjectMapper objectMapper = new ObjectMapper();
        String data = GetDataUtil.getData();
        SarsBean sarsBean = objectMapper.readValue(data, SarsBean.class);
        return Result.ok().data("msg","获取疫情数据").data("sarsBean",sarsBean);
    }

    public Result getSarsDataBy163(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        // 调用方法将json字符串转换为对应的实例
        ObjectMapper objectMapper = new ObjectMapper();
        String data = GetData2Util.getData();
        Root root = objectMapper.readValue(data, Root.class);
        if(root!=null){
            root.setCode(20000);
            Cov19Bean cov19Bean = new Cov19Bean();
            cov19Bean.setChinaDayList(root.getData().getChinaDayList());
            cov19Bean.setChinaTotal(root.getData().getChinaTotal());
            List<AreaTree> chinaList = root.getData().getAreaTree().stream().filter(e -> "中国".equals(e.getName())).collect(Collectors.toList());
            List<LocationNum> chinaCityData=new ArrayList<>();
            List<LocationNum> chinaCityTodayData=new ArrayList<>();
            if(chinaList!=null&&chinaList.size()>0){
                AreaTree china = chinaList.get(0);
                chinaCityData=foreachList(china,TOTAL_MODE);
                chinaCityTodayData=foreachList(china,TODAY_MODE);
            }
            List<AreaTree> otherCountries = root.getData().getAreaTree().stream().filter(e -> !"中国".equals(e.getName())).collect(Collectors.toList());
            List<LocationNum> gTotal=new ArrayList<>();
            List<LocationNum> gToday=new ArrayList<>();
            if(otherCountries!=null&&otherCountries.size()>0){
                gToday=foreachCountryList(otherCountries, TODAY_MODE);
                gTotal=foreachCountryList(otherCountries, TOTAL_MODE);
            }
            cov19Bean.setData(chinaCityData);
            cov19Bean.setToday(chinaCityTodayData);
            cov19Bean.setG_data(gTotal);
            cov19Bean.setG_today(gToday);
            cov19Bean.setCode(20000);
            return Result.ok().data("code",20000).data("msg","获取数据成功").data("cov19Bean",cov19Bean);
        }
        return Result.ok().data("code",10000).data("msg","获取数据失败").data("cov19Bean",new Cov19Bean());
    }
    private List<LocationNum> foreachCountryList(List<AreaTree>  countries,int mode) {
        List<LocationNum> countryData=new ArrayList<>();
        if(countries!=null){
            for (AreaTree country : countries) {
                LocationNum l = new LocationNum();
                l.setId(country.getId());
                l.setName(country.getName());
                Integer value= (mode==0?country.getTotal().getConfirm():country.getToday().getConfirm());
                l.setValue(value);
                l.setLastUpdateTime(country.getLastUpdateTime());
                ArrayList<LocationNum> temp = new ArrayList<>();
                for (AreaTree city : country.getChildren()) {
                    LocationNum i = new LocationNum();
                    i.setId(city.getId());
                    i.setName(city.getName());
                    Integer value2= (mode==0?city.getTotal().getConfirm():city.getToday().getConfirm());
                    i.setValue(value2);
                    i.setLastUpdateTime(city.getLastUpdateTime());
                    i.setChildren(new ArrayList<>());
                    temp.add(i);
                }
                l.setChildren(temp);
                countryData.add(l);
            }
        }
        return countryData;
    }

        private List<LocationNum> foreachList(AreaTree country,int mode) {
        List<LocationNum> countryData=new ArrayList<>();
        for (AreaTree province : country.getChildren()) {
            LocationNum l = new LocationNum();
            l.setId(province.getId());
            l.setName(province.getName());
            Integer value= (mode==0?province.getTotal().getConfirm():province.getToday().getConfirm());
            l.setValue(value);
            l.setLastUpdateTime(province.getLastUpdateTime());

            ArrayList<LocationNum> temp = new ArrayList<>();
            for (AreaTree city : province.getChildren()) {
                LocationNum i = new LocationNum();
                i.setId(city.getId());
                i.setName(city.getName());
                Integer value2= (mode==0?city.getTotal().getConfirm():city.getToday().getConfirm());
                i.setValue(value2);
                i.setLastUpdateTime(city.getLastUpdateTime());
                i.setChildren(new ArrayList<>());
                temp.add(i);
            }
            l.setChildren(temp);
            countryData.add(l);
        }
        return countryData;
    }
}
