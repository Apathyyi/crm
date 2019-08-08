package com.sy.crm.web.action;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sy.crm.Utils.UploadUtils;
import com.sy.crm.domain.Customer;
import com.sy.crm.domain.PageBean;
import com.sy.crm.service.CustomerService;

/**2019年6月12日
 * @author SY
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//属性驱动
	private Integer currentpage=1;
	public void setCurrentpage(Integer currentpage) {
		if(currentpage==null){
			currentpage=3;
		}
		else{
		this.currentpage = currentpage;
		}
	}
	//每页显示记录数
	private Integer pagesize=3;
	public void setPagesize(Integer pagesize) {
		if(pagesize==null){
			pagesize=3;
		}
		else{
		this.pagesize = pagesize;
		}
	}
	//字符串类型  ：上传项名称+Filename
	//文件类型： 上传项名称
	//字符串类型：上传项名称+ContentType
	private String uploadFileName;
	private File upload;
	private String uploadContentType;
	public void setUploadFileName(String uploadFilename) {
		this.uploadFileName = uploadFilename;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String saveUI(){
		return "saveUI";
	}
	/*
	* 保存客户
	*/
	public String save() throws IOException{
		//上传文件保存客户资质等
		if(upload!=null){
			//文件上传路径
			String path="D:/upload";
			//上传相同文件：随机文件名
			String randomFilename=UploadUtils.getrandomFilename(uploadFileName);
			//文件过多：目录分离
			String realPath = UploadUtils.getpath(randomFilename);
			String url=path+realPath;
			File file = new File(url);
			if(!file.exists()){
				file.mkdirs();
			}
			File dicFile = new File(url+"/"+randomFilename);
			FileUtils.copyFile(upload, dicFile);
			customer.setCust_image(url+"/"+randomFilename);
		}
		customerService.save(customer);
		return "saveSuccess";
	}
		/*
		 * 查找所有客户
		 */
	public String findAll(){
		//分页查询客户列表;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		if(customer.getCust_name()!=null){
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictsorce()!=null){
		if(customer.getBaseDictsorce().getDict_id()!=null&&!"".equals(customer.getBaseDictsorce().getDict_id())){
			detachedCriteria.add(Restrictions.eq("getBaseDictsorce.dict_id", customer.getBaseDictsorce().getDict_id()));
		}
		}
		if(customer.getBaseDictindustry()!=null){
		if(customer.getBaseDictindustry().getDict_id()!=null&&!"".equals(customer.getBaseDictindustry().getDict_id())){
			detachedCriteria.add(Restrictions.eq("getBaseDictindustry.dict_id", customer.getBaseDictindustry().getDict_id()));
		}
		}
		if(customer.getBaseDictlevel()!=null){
		if(customer.getBaseDictlevel().getDict_id()!=null&&! "".equals(customer.getBaseDictlevel().getDict_id())){
			detachedCriteria.add(Restrictions.eq("getBaseDictlevel.dict_id",customer.getBaseDictlevel().getDict_id()));
		}
		}
		//调用业务层
		PageBean<Customer> pageBean=  customerService.findByPage(detachedCriteria,currentpage,pagesize);
		//存放到值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	/*
	 * 异步查询
	 */
		public String findAllCustomer(){
			List<Customer> list = customerService.findAll();
			System.out.println("查询蓑鲉客户");
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Customer.class, "cust_id","cust_name");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			try {
				System.out.println(JSONObject.toJSONString(list,filter));
				ServletActionContext.getResponse().getWriter().write(JSONObject.toJSONString(list,filter));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return NONE;
		}
	/*
	 * 删除客户
	 */
	public String delete( ){
		//先查询在删除
		customer = customerService.findById(customer.getCust_id());
		//删除客户资质图片
		if(customer.getCust_image()!=null){	
			File file = new File(customer.getCust_image());
			if(file.exists()){
				file.delete();
			}
		}
		customerService.delete(customer);
		return "deleteSuccess";
	}
	public String edit(){
		//根据id查询并回显数据
		customer = customerService.findById(customer.getCust_id());
		//1.手动压入值栈 <s:property value="cust_name"/>
		//2.模型驱动cutomer本身就在值栈中<s:property value="model.cust_name"/>
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
	}
	public String update() throws IOException{
		if(upload!=null){
			String cust_image=customer.getCust_image();
			if(cust_image!=null||!"".equals(cust_image)){
				File file = new File(cust_image);
				file.delete();
										}
		
			String path="D:/upload";
			//上传相同文件：随机文件名
			String randomFilename=UploadUtils.getrandomFilename(uploadFileName);
			//文件过多：目录分离
			String realPath = UploadUtils.getpath(randomFilename);
			String url=path+realPath;
			File file = new File(url);
			if(!file.exists()){
				file.mkdirs();
			}
			File dicFile = new File(url+"/"+randomFilename);
			FileUtils.copyFile(upload, dicFile);
			customer.setCust_image(url+"/"+randomFilename);
		}
			customerService.update(customer);
			return "updateSuccess";
	}
	public String countBysource(){
		//List<SourceBean> list = customerService.findCountBysource();
		//饼图数据
		DefaultPieDataset data = new DefaultPieDataset();
		//设置饼图数据
		data.setValue("网络营销",4);
		data.setValue("电话营销",1);
		//饼状图
			JFreeChart PieChart = ChartFactory.createPieChart("客户来源统计图", data,true,true,false);
			PieChart.setTitle(new TextTitle("客户来源统计图"));
			LegendTitle legend=PieChart.getLegend(0);//设置Legend
	        legend.setItemFont(new Font("宋体",Font.BOLD,14));
			PiePlot plot=(PiePlot)PieChart.getPlot(); 
			plot.setLabelFont(new Font("隶书",Font.BOLD,16));
			// 图片中显示百分比:默认方式 
			//plot.setLabelGenerator(new StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));
			// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位 
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%"))); 
			// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例 
			plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
			
			try {
				ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(),PieChart, 400, 300);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return NONE;
	}
	public String countByindustry(){
				//图表数据
				DefaultCategoryDataset dataset=new DefaultCategoryDataset();
				//设置图表数据
				dataset.setValue(5, "a", "电子商务");
				dataset.setValue(2, "b", "教育培训");
				dataset.setValue(1, "c", "酒店旅游");
				dataset.setValue(8, "d", "对外贸易");
				dataset.setValue(3, "e", "网络营销");
				//图表图
				JFreeChart Barchart=ChartFactory.createBarChart("hi", "行业分布", "统计数量", dataset, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
				Barchart.setTitle(new TextTitle("客户行业统计表",new Font("宋体",Font.BOLD+Font.ITALIC,20)));//可以重新设置标题，替换“hi”标题
				LegendTitle barlegend = Barchart.getLegend();
				barlegend.setItemFont(new Font("宋体",Font.BOLD,14));
				//横坐标
		        CategoryPlot cplot = Barchart.getCategoryPlot();
				CategoryAxis categoryAxis=cplot .getDomainAxis();//获得横坐标
		        categoryAxis.setLabelFont(new Font("隶书",Font.BOLD,16));//设置横坐标字体
		        
		        categoryAxis.setTickLabelFont(new Font("隶书",Font.BOLD,16));//轴数值
		        //纵坐标
		        ValueAxis rangeAxis = cplot.getRangeAxis();
		        rangeAxis.setLabelFont(new Font("隶书",Font.BOLD,16));
		        rangeAxis.setTickLabelFont(new Font("隶书",Font.BOLD,16));
				try {
					ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
							.getOutputStream(),Barchart, 400, 300);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return NONE;
	}
	public String searchUI(){
		return "searchUI";
	}
}