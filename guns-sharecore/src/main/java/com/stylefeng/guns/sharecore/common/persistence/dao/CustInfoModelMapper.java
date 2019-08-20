package com.stylefeng.guns.sharecore.common.persistence.dao; 

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustInfoShowModel;
import com.stylefeng.guns.sharecore.common.persistence.model.CustMerchantBO;

public interface CustInfoModelMapper {
     
    int insert(CustInfoModel record);
    /**
     * 商户创建账号。。。
     * @param record
     * @return
     */
    int insertForMerchant(CustInfoModel record);
 
    CustInfoModel selectByPrimaryKey(Long custNo);
   
    CustInfoModel selectCustInfoAndCustAccountInfoByPrimaryKey(Long custNo);

    CustInfoModel selectByOpenId(String openId);
    
    CustInfoModel selectByUnionId(String unionId);
    
    CustInfoModel selectByWxAppOpenId(String wxAppOpenId);    
    
    CustInfoModel getMerchantCustInfo(String openId);
    /**
     * 
     * <p>获取商户的客户账号，只会有一个</p>
     * @param merchantId
     * @return
     */
    CustInfoModel selectMerchantCustInfoByMerchantId(@Param("merchantId")Long merchantId);
    
    //CustInfoModel selectByMerchantId(@Param("merchantId")Long merchantId,@Param("custType")Integer custType);
    
    //CustInfoModel selectByMerchantIdAndCustType4Distribution(@Param("merchantId")Long merchantId,@Param("custType")Integer custType);
    
    List<CustInfoModel> selectCustInfoAndAccountInfoByMerchantIdAndCustType(@Param("merchantId")Long merchantId,@Param("custType")Integer custType);
     
    Page<CustInfoShowModel>  custInfoQuery(CustInfoShowModel model);
    
    Page<CustInfoModel>  capitalAccountQuery(CustInfoShowModel model);

    int updateByPrimaryKeySelective(CustInfoModel record);

    int updateByPrimaryKeySelectiveByOpenId(CustInfoModel record);
    
    int updateByPrimaryKey(CustInfoModel record);
    
 
 
//    List<CustInfoModel> selectOnlyByMerchantId(@Param("merchantId")Long merchantId);
    
    List<CustMerchantBO> selectByMerchantType(@Param("merchantType")Integer merchantType);
    
    List<CustMerchantBO> selectAllCustInfo();
    
    CustMerchantBO getByCustNo(@Param("custNo")Long custNo);
    
    List<CustMerchantBO> getByMerchantId(@Param("merchantId")Long merchantId);
    /**
     * 得到所有没有unionId的客户
     * @return
     */
    List<CustInfoModel> selectAllNoUnionId(@Param("startRowIndex")Long startRowIndex,@Param("endRowIndex")Long endRowIndex);
    /**
     * 得到所有关注了公众号，未得到unionid用户数...
     * @return
     */
    Long selectAllNoUnionIdCount();
    /**
     * 爱心排名
     */
    List<CustInfoModel> getRank();
   
  
	//更新客户手机号
	void updatePhoneByPrimaryKey(CustInfoModel custInfoModel);

	/**
	 * 根据客户id查询客户
	 * @param custNo
	 * @return
	 */
	CustInfoShowModel queryByCustNo(Long custNo);

	
	/**
	 * 根据客户的主键修改
	 * @param ci
	 */
	void updateMerchantIdByPrimaryKey(CustInfoModel ci);

	List<Long> queryCustInfoByNickName(String nickName);
 
}