package com.chains.dataImport.excelImport;

import com.chains.pwqxfwjk.model.CustomerTransformerInfo;
import com.chains.pwqxfwjk.model.TransformerInfo;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ImportXTCustomerTransformer {
    public static void main(String[] args) {

    }
    @Autowired
    private SessionFactory sessionFactory;
    //统计文件
    public void cleanData() throws Exception {
      /*  Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" select distinct ct.transformerName as transformerName from CustomerTransformerInfo ct where not exists (select 1 from TransformerInfo t where ct.transformerName = t.transformerName)");
        List<CustomerTransformerInfo> list = query.setResultTransformer(Transformers.aliasToBean(CustomerTransformerInfo.class)).list();
        for (CustomerTransformerInfo customerTransformerInfo :
                list) {
            Matcher matcher = Pattern.compile("(\\d+-?\\d*号?)").matcher(customerTransformerInfo.getTransformerName());
            String regelarTransformerName = "";
            if(matcher.find()) {
                String matchStr = matcher.group();
//                Query query1 = session.createQuery("from TransformerInfo t where t.transformerName like :transformerName");
                SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM TRANSFORMER_INFO t WHERE t.TRANSFORMER_NAME REGEXP :transformerName");
                sqlQuery.setString("transformerName", "^" + matchStr);
                List<TransformerInfo> list1 = sqlQuery.addEntity(TransformerInfo.class).list();
                if(list1.size() <=2) {
                    for (TransformerInfo transformerInfo:
                            list1) {
                        if(!transformerInfo.getTransformerName().contains("东风")) {
                            regelarTransformerName = transformerInfo.getTransformerName();
                        }
                    }
                    if(regelarTransformerName.length() > 0) {
                        Query query2 = session.createQuery("update CustomerTransformerInfo ct set ct.transformerName = :regelarTransformerName where ct.transformerName = :orignalTransfornmerName");
                        query2.setString("regelarTransformerName", regelarTransformerName)
                                .setString("orignalTransfornmerName", customerTransformerInfo.getTransformerName()).executeUpdate();
                    }
                }
            }
        }
        transaction.commit();*/
    }
}
