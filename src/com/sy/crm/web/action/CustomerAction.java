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

/**2019��6��12��
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
	//��������
	private Integer currentpage=1;
	public void setCurrentpage(Integer currentpage) {
		if(currentpage==null){
			currentpage=3;
		}
		else{
		this.currentpage = currentpage;
		}
	}
	//ÿҳ��ʾ��¼��
	private Integer pagesize=3;
	public void setPagesize(Integer pagesize) {
		if(pagesize==null){
			pagesize=3;
		}
		else{
		this.pagesize = pagesize;
		}
	}
	//�ַ�������  ���ϴ�������+Filename
	//�ļ����ͣ� �ϴ�������
	//�ַ������ͣ��ϴ�������+ContentType
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
	* ����ͻ�
	*/
	public String save() throws IOException{
		//�ϴ��ļ�����ͻ����ʵ�
		if(upload!=null){
			//�ļ��ϴ�·��
			String path="D:/upload";
			//�ϴ���ͬ�ļ�������ļ���
			String randomFilename=UploadUtils.getrandomFilename(uploadFileName);
			//�ļ����ࣺĿ¼����
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
		 * �������пͻ�
		 */
	public String findAll(){
		//��ҳ��ѯ�ͻ��б�;
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
		//����ҵ���
		PageBean<Customer> pageBean=  customerService.findByPage(detachedCriteria,currentpage,pagesize);
		//��ŵ�ֵջ
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	/*
	 * �첽��ѯ
	 */
		public String findAllCustomer(){
			List<Customer> list = customerService.findAll();
			System.out.println("��ѯ�����ͻ�");
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
	 * ɾ���ͻ�
	 */
	public String delete( ){
		//�Ȳ�ѯ��ɾ��
		customer = customerService.findById(customer.getCust_id());
		//ɾ���ͻ�����ͼƬ
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
		//����id��ѯ����������
		customer = customerService.findById(customer.getCust_id());
		//1.�ֶ�ѹ��ֵջ <s:property value="cust_name"/>
		//2.ģ������cutomer�������ֵջ��<s:property value="model.cust_name"/>
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
			//�ϴ���ͬ�ļ�������ļ���
			String randomFilename=UploadUtils.getrandomFilename(uploadFileName);
			//�ļ����ࣺĿ¼����
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
		//��ͼ����
		DefaultPieDataset data = new DefaultPieDataset();
		//���ñ�ͼ����
		data.setValue("����Ӫ��",4);
		data.setValue("�绰Ӫ��",1);
		//��״ͼ
			JFreeChart PieChart = ChartFactory.createPieChart("�ͻ���Դͳ��ͼ", data,true,true,false);
			PieChart.setTitle(new TextTitle("�ͻ���Դͳ��ͼ"));
			LegendTitle legend=PieChart.getLegend(0);//����Legend
	        legend.setItemFont(new Font("����",Font.BOLD,14));
			PiePlot plot=(PiePlot)PieChart.getPlot(); 
			plot.setLabelFont(new Font("����",Font.BOLD,16));
			// ͼƬ����ʾ�ٷֱ�:Ĭ�Ϸ�ʽ 
			//plot.setLabelGenerator(new StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));
			// ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ 
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%"))); 
			// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� 
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
				//ͼ������
				DefaultCategoryDataset dataset=new DefaultCategoryDataset();
				//����ͼ������
				dataset.setValue(5, "a", "��������");
				dataset.setValue(2, "b", "������ѵ");
				dataset.setValue(1, "c", "�Ƶ�����");
				dataset.setValue(8, "d", "����ó��");
				dataset.setValue(3, "e", "����Ӫ��");
				//ͼ��ͼ
				JFreeChart Barchart=ChartFactory.createBarChart("hi", "��ҵ�ֲ�", "ͳ������", dataset, PlotOrientation.VERTICAL, true, true, false); //����һ��JFreeChart
				Barchart.setTitle(new TextTitle("�ͻ���ҵͳ�Ʊ�",new Font("����",Font.BOLD+Font.ITALIC,20)));//�����������ñ��⣬�滻��hi������
				LegendTitle barlegend = Barchart.getLegend();
				barlegend.setItemFont(new Font("����",Font.BOLD,14));
				//������
		        CategoryPlot cplot = Barchart.getCategoryPlot();
				CategoryAxis categoryAxis=cplot .getDomainAxis();//��ú�����
		        categoryAxis.setLabelFont(new Font("����",Font.BOLD,16));//���ú���������
		        
		        categoryAxis.setTickLabelFont(new Font("����",Font.BOLD,16));//����ֵ
		        //������
		        ValueAxis rangeAxis = cplot.getRangeAxis();
		        rangeAxis.setLabelFont(new Font("����",Font.BOLD,16));
		        rangeAxis.setTickLabelFont(new Font("����",Font.BOLD,16));
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