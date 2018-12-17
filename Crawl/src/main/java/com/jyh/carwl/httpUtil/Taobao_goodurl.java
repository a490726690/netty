package com.jyh.carwl.httpUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jyh-mac on 2018/11/2.
 */
public class Taobao_goodurl {


    /**
     * 淘宝
     * @param args
     */
    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://s.taobao.com/search?q=aj1黑紫&s=88");
        CloseableHttpResponse response = null;
        httpget.setHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpget.setHeader("accept-encoding","gzip, deflate, br");
        httpget.setHeader("accept-language","zh-CN,zh;q=0.9,en;q=0.8");
        httpget.setHeader("cache-control","max-age=0");
        httpget.setHeader("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        httpget.setHeader("cookie","l=AgcHZng5IMGivOUwFvPKzt9LF7HQYNvu; cna=JmCPEXL5LUACAbf3lTrFECRd; tg=0; thw=cn; t=ff07dda168e9572970784e43a65a27df; hng=CN%7Czh-CN%7CCNY%7C156; v=0; cookie2=159577bfeb9623dcbc41c6384a1e8533; _tb_token_=e53385bb75e39; alitrackid=www.taobao.com; lastalitrackid=www.taobao.com; linezing_session=6I9TBrJogVIt2AfKOH9fXdBX_1544087264183Oufp_1; tracknick=a490726690; lgc=a490726690; dnk=a490726690; enc=vilOTjI2CR%2BGDXp9NtZkbEk5KOxcvwbnjOnXV6a96DPt4QDqF4aPjdutSnvkRugGstUUoGTkBEYyiF%2FJ8M%2FBBA%3D%3D; unb=2144957586; sg=060; _l_g_=Ug%3D%3D; skt=1bcd6baa6627d524; cookie1=W51bStKuE0afpd5avWjHWzVphnL5NqZn%2BavgCY1CaZ8%3D; csg=e257de0e; uc3=vt3=F8dByRzJQczfv%2FQL%2FCQ%3D&id2=UUkKeAbKUV2XtQ%3D%3D&nk2=AiUBuYMMBEMNkw%3D%3D&lg2=V32FPkk%2Fw0dUvg%3D%3D; existShop=MTU0NDc3NzExOA%3D%3D; _cc_=UtASsssmfA%3D%3D; _nk_=a490726690; cookie17=UUkKeAbKUV2XtQ%3D%3D; uc1=cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&cookie21=UtASsssmeWlNBv%2Fxp3RP&cookie15=Vq8l%2BKCLz3%2F65A%3D%3D&existShop=false&pas=0&cookie14=UoTYMhm2MyGsSA%3D%3D&cart_m=0&tag=8&lng=zh_CN; mt=ci=7_1; JSESSIONID=1A1C7DC174F3696C9219B2E1B78DE2C4; isg=BNTUglxqdJSH5-d_sQHE7TbKpRRML9C1duseCm61Zd_iWXSjlj7dp1pbXRHkoTBv");
        httpget.setHeader("upgrade-insecure-requests","1");
        BufferedReader in = null;
        String result = "";
        try {
            response = httpclient.execute(httpget);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity!=null){
                in = new BufferedReader(new InputStreamReader(httpEntity.getContent(),"utf-8"));
                String line;
                while ((line=in.readLine())!=null){
                    result+=line;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

                try {
                    if (response!=null) response.close();
                    if (in!=null) in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        result = result.substring(result.indexOf("\"itemlist\":")+11,result.indexOf(",\"recommendAuctions\""))+"}}";
        JSONObject json = new JSONObject();
        json= (JSONObject) JSONObject.parse(result);
        JSONArray itemList = json.getJSONObject("data").getJSONArray("atcions");
        for (int i=0;i<itemList.size();i++){
            JSONObject item =new JSONObject();
            item.put("title",itemList.getJSONObject(i).getString("raw_title"));//商品名
            item.put("price",itemList.getJSONObject(i).getString("view_price"));//搜索价格
            item.put("commentCount",itemList.getJSONObject(i).getString("comment_count"));//评论总数
            item.put("itemId",itemList.getJSONObject(i).getString("nid"));//商品id
            item.put("picUrl",itemList.getJSONObject(i).getString("pic_url"));//商家图片
            item.put("detailUrl",itemList.getJSONObject(i).getString("comment_url"));//商品链接
            item.put("nick",itemList.getJSONObject(i).getString("nick"));//商家id名
            item.put("viewSales",itemList.getJSONObject(i).getString("view_sales"));//付款人数
            item.put("userId",itemList.getJSONObject(i).getString("user_id"));//商家id
            item.put("shopLink",itemList.getJSONObject(i).getString("shopLink"));//商家主页
            item.put("category",itemList.getJSONObject(i).getString("category"));//category 品类id?
            item.put("nick",itemList.getJSONObject(i).getString("nick"));//商家id名
            item.put("pid",itemList.getJSONObject(i).getString("pid"));//pid 暂时不知道是什么 可能是推广码?
        }
        System.out.println(result);
    }
}
