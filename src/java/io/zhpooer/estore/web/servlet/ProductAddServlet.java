package io.zhpooer.estore.web.servlet;

import io.zhpooer.estore.entity.Product;
import io.zhpooer.estore.service.ProductService;
import io.zhpooer.estore.service.impl.ProductServiceImpl;
import io.zhpooer.estore.utils.ServiceFactory;
import io.zhpooer.estore.utils.UploadUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

@SuppressWarnings("serial")
public class ProductAddServlet extends HttpServlet {
    ProductService productService = (ProductService)ServiceFactory.generate(ProductServiceImpl.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO 用户权限检查
        if (ServletFileUpload.isMultipartContent(req)) {
            HashMap<String, String> paramMap = new HashMap<>();

            DiskFileItemFactory fac = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(fac);
            List<FileItem> items = null;
            try {
                items = sfu.parseRequest(req);
            } catch (FileUploadException e) {
                throw new RuntimeException(e);
            }
            for (FileItem item : items) {
                if (item.isFormField()) {
                    paramMap.put(item.getFieldName(), item.getString("UTF-8"));
                } else {
                    // TODO 图片缩小优化
                    // TODO Product 非法检查
                    InputStream in = item.getInputStream();
                    String realName = item.getName();
                    String filename = UploadUtil
                            .generateRandomFilename(realName);
                    String uploadPath = getServletContext().getRealPath(
                            "/upload");
                    String hashDir = UploadUtil.generateRadnomDir(filename);
                    File uploadFilePath = new File(uploadPath + hashDir);
                    uploadFilePath.mkdirs();
                    IOUtils.copy(in, new FileOutputStream(uploadPath + hashDir
                            + "/" + filename));
                    item.delete();
                    paramMap.put("imgurl", "/upload" + hashDir + "/" + filename);
                }
            }
            Product p = new Product();
            try {
                BeanUtils.populate(p, paramMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            productService.addProduct(p);
            resp.getWriter().print("添加成功");
            resp.setHeader("Refresh", "3;URL=" + req.getContextPath() + "/product_add.jsp");
        } else {
            throw new RuntimeException("不支持的编码格式");
        }
        // Servlet
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
