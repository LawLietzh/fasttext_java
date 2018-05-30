package fasttext;

//linux中 ngram 设为2 的时候 老是提示浮点数 溢出，本来想在Windows下试试，但是，提示内存不足
import java.util.List;  

import fasttext.FastText;  
import fasttext.Main;  
import fasttext.Pair;  
public class trainModel {
	public static void main(String[] args) throws Exception {  
		  
    String[] text = {  
                "supervised",  
                "-input",  
                "D://data//YQ_resultXin//605//train.txt",  
                "-output", "E://fasttext_model//news.model2", "-dim",  
                "100", "-lr", "0.05", "-wordNgrams", "2", "-minCount", "5",  
                "-bucket", "20000000", "-epoch", "5", "-thread", "12" };  
        Main op = new Main();  
        op.train(text);  
        
        
        FastText fasttext = new FastText();  
      //  String[] test = { "中国 经济网 编者按 ： 近日 ， 多家 媒体报道 国美 旗下 互金 平台 国美 在线 金融 涉嫌 自融 、 资金 流向 自家 地产 公司 、 为 失信 企业 贷款 等 问题 。   国美 在线 金融 类似 于 理财 分销 网站 ， 与 国美 系 的 平台 进行 合作 ， 销售 其他 公司 的 产品 。 据 零壹 财经 ， 其 代销 的 产品 “ 美美 赚 - 国金 1 号 ” ， 该 产品 合作 机构 为 国美 关联 公司 鹏润 金服 。   以国金 1 号 6958 为例 ， 该 标的 担保 机构 为 “ 鹏润 控股 有限公司 ” ， 借款 机构 为 “ 西安 鹏润 置业 有限公司 ” ， 标的 中 还 还 显示 “ 西安 鹏润 置业 有限公司 由鹏润 控股 有限公司 出资 设立 ” 。   工商 信息 显示 ， 西安 鹏润 置业 有限公司 、 鹏润 控股 有限公司 法人代表 均 为 黄秀虹 。 这 两家 公司 与 国美 在线 金融 都 存在 着 关联 关系 。 以此 来看 ， 国美 在线 金融 可能 涉嫌 关联 企业 融资 和 关联 企业 担保 。   此外 ， 国金 1 号 多款 产品 借款方 均 为 国美 旗下 的 房地产 公司 ， 其中 就 包括 西安 鹏润 置业 有限公司 、 重庆 鹏泽 房地产 开发 有限公司 。   另据 网贷 天眼 ， 在 国美 旗下 P2P 平台 美美 理财 上 ， 多个 项目 的 借款 企业 有 失信 被 执行 记录 ， 或是 涉及 多 起 合同纠纷 等 情况 。 用 投资人 的话 说 ， “ 借款 企业 的 底子 不太 干净 。 ”   中国 经济网 记者 试图 联系 国美 金融 ， 但 其 官网 披露 的 采访 电话 无人 接听 。   资金 流向 自家 地产 公司   据 “ 互金 侦探 ” 消息 称 ， 有 投资人 爆料 ， 国美 系 的 “ 国美 在线 金融 ” 竟然 涉嫌 关联 融资 ， 资金 流向 了 旗下 的 地产 公司 。   先 来 看看 国美 在线 电子商务 有限公司 的 股权结构 ：   北京 汇达智丰 咨询 有限公司 公司 的 法人代表 是 黄秀虹 ， 再往 上 的 股权 结构图 如下 ：   上述 公司 中 ， 国美 控股集团 有限公司 、 北京 鹏润 投资 有限公司 、 北京 英格润 美 咨询 有限公司 法人代表 皆 为 黄秀虹 ， 也 就是 现任 鹏润 控股集团 董事长 ， 原 国美 控股集团 总裁 。   国美 在线 金融 类似 于 理财 分销 网站 ， 与 国美 系 的 平台 进行 合作 ， 销售 其他 公司 的 产品 。 比如 ： 投金宝 - 华人 1 号 半年 投 5301 ， 网站 显示 合作伙伴 为 ： 深圳 前 海 华人 互联网 金融服务 集团 有限公司 ， 公司 运营 着 华人 金融 的 网站 。   而 美美 赚 - 国金 1 号 6822 这款 产品 ， 网站 显示 合作伙伴 为 ： 北京 鹏润 金服 在线 信息技术 有限公司 ， 公司 还 运营 着 美美 理财 的 网站 。   北京 鹏润 金服 在线 信息技术 有限公司 股权结构 北京 鹏润 金服 在线 信息技术 有限公司 股权结构 上图 中 ， 每 一家 公司 的 法人 都 是 黄秀虹 。   而 由 北京 鹏润 金服 在线 信息技术 有限公司 推荐 的 这些 借款 需求 ， 借款方 为 “ 重庆 鹏泽 房地产 开发 有限公司 ” ， 这家 公司 实际上 是鹏润 控股 有限公司 旗下 的 一家 公司 。   这种 情况 不 只 一例 ， 美美 赚 - 国金 1 号 6826 的 项目 中 ， 借款方 为 “ 西安 鹏润 置业 有限公司 ” ， 该 公司 又 是 一家 国   据 零壹 财经 ， 国美 在线 金融 为 国美 旗下 四家 互金 平台 之一 ， 其 并 没有 自己 的 独立 官网 ， 而是 作为 国美 在线 下属 金融 频道 的 形式 存在 ， 平台 业务 主要 以 分销 国美 系 其他 平台 债权 和 其他 一些 票据 理财 、 基金 等 为主 。 目前为止 ， 国美 在线 金融 还 并 没有 上线 任何 的 资金 存管 系统 。   本次 被 曝出 问题 的 产品 主要 是 其 代销 的 产品 “ 美美 赚 - 国金 1 号 ” ， 该 产品 合作 机构 为 国美 关联 公司 鹏润 金服 。 按照 其官 网所 显示 的 信息 来看 ， 目前 还有 一些 问题 需要 解决 — — 关联 企业 担保 、 关联 企业 融资 。   以国金 1 号 6958 为例 ， 该 标的 担保 机构 为 “ 鹏润 控股 有限公司 ” ， 借款 机构 为 “ 西安 鹏润 置业 有限公司 ” ， 标的 中 还 还 显示 “ 西安 鹏润 置业 有限公司 由鹏润 控股 有限公司 出资 设立 ” 。   安鹏润 置业 有限公司 、 鹏润 控股 有限公司 法人代表 均 为 黄秀虹 （ 原 国美 集团 总裁 黄光裕 之妹 。 这 两家 公司 与 国美 在线 金融 都 存在 着 关联 关系 。 以此 来看 ， 国美 在线 金融 可能 涉嫌 关联 企业 融资 和 关联 企业 担保 。 据 去年 8 月 24 日 发布 的 《 网络 借贷 信息 中介机构 业务 活动 管理 暂行办法 》 （ 下文 简称 暂行办法 ） 规定 ， 网贷 平台 不得 为 自身 或 变相 为 自身 融资 。 类似 上 文中说 的 这种 关联 企业 融资 的 这种 行为 并 没有 相关 规定 。   中国政法大学 教授 李爱君 认为 ， “ 为 自身 或 变相 为 自身 融资 ” 一定 要 注意 “ 变相 ” 二字 ， 这是 体现 “ 实质 ” 行为 ， 不能 通过 结构设计 进行 变相 自身 融资 。 这 对 目前 一些 机构 影响 较大 ， 实践 中 部分 平台 通过 一些 结构设计 实质 上 主要 是 为 自身 或 关联 公司 进行 融资 的 行为 是 被 禁止 的 。   为信 企业 贷款   据 网贷 天眼 ， 据 投资人 爆料 ， 在 国美 旗下 P2P 平台 美美 理财 上 ， 多个 项目 的 借款 企业 有 失信 被 执行 记录 ， 或是 涉及 多 起 合同纠纷 等 情况 。 用 投资人 的话 说 ， “ 借款 企业 的 底子 不太 干净 。 ”"};  
      // String[] test = {"1 月 18 日 法制 日报 法制日报 报道 了 携程 旅游 旅游网 一 云南 游 项目 中 明确 规定 明确规定 记者 及 河南 焦作 、 马店 驻马店 、 湖南 醴陵 、 广东 揭阳 等 地人 禁止 参团 的 现象 。 当日 ， 携程 旅游 事业 事业部 对 该 事件 予以 回应 ， 称 对此 违规 行为 违规行为 绝不 姑息 ， 已 启动 整改 措施 整改措施 。 并 表示 将 对 被 曝光 的 上架 依据 平台 规则 进行 相应 处罚 。 据 法制 日报 法制日报 报 道 ， 湖南 醴陵 的 孙姓 市民 反映 ， 用 手机 携程 报名 参团 云南 游时 ， 却 遇 “ 歧视 歧视性 条款 ” ： 在 一份 “ 版纳 西双版纳 + 九乡 + 普洱 6 日 5 晚三飞 5 星级 酒店 ” 的 旅游 产品 的 “ 预定 须知 ”着 ： “ 本 产品 不 接受 记者 … … 揭阳 人 、 焦作 、 马店 驻马店 、 醴陵 客人 ” 。 该 旅游 产品 的 供应 供应商 为 “ 昆明 康辉 永恒 旅行 行社 旅行社 ” 。 而 当 记者 致电 该 供应 供应商 客服 人员 时 ，竟 解释 ： “ 有些 记者 不是 来 旅游 的 ， 都 是 来 找事 的 。 ” 该 事件 报道 后 ， 澎湃 新闻 （ www . thepaper . cn ） 记者 联系 携程 旅游 旅游网 。 携程 旅游 事业 事业部 随后 发布 声明 称 ： 近日 发现  客户 以及 媒体 反馈 ， 有 旅行 行社 旅行社 供应 供应商 产品 预订 条款 中有 禁止 部分 群体 预订 的 内容 ， 对于 这一 情况 ， 携程 旅游 平台 供应 供应商 管理 与 运营 部门 第一 时间 介入 处理 。 经 调查 ， 这些 内容 是 旅行 行社 旅行社 产品 上线 之前 的 固有 条款 ， 这些 限制 限制性 条款 损害 了 旅游 游者 旅游者 的 合法 法权 权益 合法权 合法权益 。 携程 旅游 事业 事业部 还 表示 ， 对于 此类 违规 行为 违规行为 决不 姑息 ， 迅速 启动 整改 措施 整改措施"};
      //  String[] test ={"诈骗"," 涉嫌", "犯人", "投到"," 倒闭"};
        
      //   String  line = "1 月 18 日 法制 日报 法制日报 报道 了 携程 旅游 旅游网 一 云南 游 项目 中 明确 规定 明确规定 记者 及 河南 焦作 、 马店 驻马店 、 湖南 醴陵 、 广东 揭阳 等 地人 禁止 参团 的 现象 。 当日 ， 携程 旅游 事业 事业部 对 该 事件 予以 回应 ， 称 对此 违规 行为 违规行为 绝不 姑息 ， 已 启动 整改 措施 整改措施 。 并 表示 将 对 被 曝光 的 上架 依据 平台 规则 进行 相应 处罚 。 据 法制 日报 法制日报 报 道 ， 湖南 醴陵 的 孙姓 市民 反映 ， 用 手机 携程 报名 参团 云南 游时 ， 却 遇 “ 歧视 歧视性 条款 ” ： 在 一份 “ 版纳 西双版纳 + 九乡 + 普洱 6 日 5 晚三飞 5 星级 酒店 ” 的 旅游 产品 的 “ 预定 须知 ”着 ： “ 本 产品 不 接受 记者 … … 揭阳 人 、 焦作 、 马店 驻马店 、 醴陵 客人 ” 。 该 旅游 产品 的 供应 供应商 为 “ 昆明 康辉 永恒 旅行 行社 旅行社 ” 。 而 当 记者 致电 该 供应 供应商 客服 人员 时 ，竟 解释 ： “ 有些 记者 不是 来 旅游 的 ， 都 是 来 找事 的 。 ” 该 事件 报道 后 ， 澎湃 新闻 （ www . thepaper . cn ） 记者 联系 携程 旅游 旅游网 。 携程 旅游 事业 事业部 随后 发布 声明 称 ： 近日 发现  客户 以及 媒体 反馈 ， 有 旅行 行社 旅行社 供应 供应商 产品 预订 条款 中有 禁止 部分 群体 预订 的 内容 ， 对于 这一 情况 ， 携程 旅游 平台 供应 供应商 管理 与 运营 部门 第一 时间 介入 处理 。 经 调查 ， 这些 内容 是 旅行 行社 旅行社 产品 上线 之前 的 固有 条款 ， 这些 限制 限制性 条款 损害 了 旅游 游者 旅游者 的 合法 法权 权益 合法权 合法权益 。 携程 旅游 事业 事业部 还 表示 ， 对于 此类 违规 行为 违规行为 决不 姑息 ， 迅速 启动 整改 措施 整改措施";
     // String  line = " 中国 经济网 编者按 ： 近日 ， 多家 媒体报道 国美 旗下 互金 平台 国美 在线 金融 涉嫌 自融 、 资金 流向 自家 地产 公司 、 为 失信 企业 贷款 等 问题 。   国美 在线 金融 类似 于 理财 分销 网站 ， 与 国美 系 的 平台 进行 合作 ， 销售 其他 公司 的 产品 。 据 零壹 财经 ， 其 代销 的 产品 “ 美美 赚 - 国金 1 号 ” ， 该 产品 合作 机构 为 国美 关联 公司 鹏润 金服 。   以国金 1 号 6958 为例 ， 该 标的 担保 机构 为 “ 鹏润 控股 有限公司 ” ， 借款 机构 为 “ 西安 鹏润 置业 有限公司 ” ， 标的 中 还 还 显示 “ 西安 鹏润 置业 有限公司 由鹏润 控股 有限公司 出资 设立 ” 。   工商 信息 显示 ， 西安 鹏润 置业 有限公司 、 鹏润 控股 有限公司 法人代表 均 为 黄秀虹 。 这 两家 公司 与 国美 在线 金融 都 存在 着 关联 关系 。 以此 来看 ， 国美 在线 金融 可能 涉嫌 关联 企业 融资 和 关联 企业 担保 。   此外 ， 国金 1 号 多款 产品 借款方 均 为 国美 旗下 的 房地产 公司 ， 其中 就 包括 西安 鹏润 置业 有限公司 、 重庆 鹏泽 房地产 开发 有限公司 。   另据 网贷 天眼 ， 在 国美 旗下 P2P 平台 美美 理财 上 ， 多个 项目 的 借款 企业 有 失信 被 执行 记录 ， 或是 涉及 多 起 合同纠纷 等 情况 。 用 投资人 的话 说 ， “ 借款 企业 的 底子 不太 干净 。 ”   中国 经济网 记者 试图 联系 国美 金融 ， 但 其 官网 披露 的 采访 电话 无人 接听 。   资金 流向 自家 地产 公司   据 “ 互金 侦探 ” 消息 称 ， 有 投资人 爆料 ， 国美 系 的 “ 国美 在线 金融 ” 竟然 涉嫌 关联 融资 ， 资金 流向 了 旗下 的 地产 公司 。   先 来 看看 国美 在线 电子商务 有限公司 的 股权结构 ：   北京 汇达智丰 咨询 有限公司 公司 的 法人代表 是 黄秀虹 ， 再往 上 的 股权 结构图 如下 ：   上述 公司 中 ， 国美 控股集团 有限公司 、 北京 鹏润 投资 有限公司 、 北京 英格润 美 咨询 有限公司 法人代表 皆 为 黄秀虹 ， 也 就是 现任 鹏润 控股集团 董事长 ， 原 国美 控股集团 总裁 。   国美 在线 金融 类似 于 理财 分销 网站 ， 与 国美 系 的 平台 进行 合作 ， 销售 其他 公司 的 产品 。 比如 ： 投金宝 - 华人 1 号 半年 投 5301 ， 网站 显示 合作伙伴 为 ： 深圳 前 海 华人 互联网 金融服务 集团 有限公司 ， 公司 运营 着 华人 金融 的 网站 。   而 美美 赚 - 国金 1 号 6822 这款 产品 ， 网站 显示 合作伙伴 为 ： 北京 鹏润 金服 在线 信息技术 有限公司 ， 公司 还 运营 着 美美 理财 的 网站 。   北京 鹏润 金服 在线 信息技术 有限公司 股权结构 北京 鹏润 金服 在线 信息技术 有限公司 股权结构 上图 中 ， 每 一家 公司 的 法人 都 是 黄秀虹 。   而 由 北京 鹏润 金服 在线 信息技术 有限公司 推荐 的 这些 借款 需求 ， 借款方 为 “ 重庆 鹏泽 房地产 开发 有限公司 ” ， 这家 公司 实际上 是鹏润 控股 有限公司 旗下 的 一家 公司 。   这种 情况 不 只 一例 ， 美美 赚 - 国金 1 号 6826 的 项目 中 ， 借款方 为 “ 西安 鹏润 置业 有限公司 ” ， 该 公司 又 是 一家 国   据 零壹 财经 ， 国美 在线 金融 为 国美 旗下 四家 互金 平台 之一 ， 其 并 没有 自己 的 独立 官网 ， 而是 作为 国美 在线 下属 金融 频道 的 形式 存在 ， 平台 业务 主要 以 分销 国美 系 其他 平台 债权 和 其他 一些 票据 理财 、 基金 等 为主 。 目前为止 ， 国美 在线 金融 还 并 没有 上线 任何 的 资金 存管 系统 。   本次 被 曝出 问题 的 产品 主要 是 其 代销 的 产品 “ 美美 赚 - 国金 1 号 ” ， 该 产品 合作 机构 为 国美 关联 公司 鹏润 金服 。 按照 其官 网所 显示 的 信息 来看 ， 目前 还有 一些 问题 需要 解决 — — 关联 企业 担保 、 关联 企业 融资 。   以国金 1 号 6958 为例 ， 该 标的 担保 机构 为 “ 鹏润 控股 有限公司 ” ， 借款 机构 为 “ 西安 鹏润 置业 有限公司 ” ， 标的 中 还 还 显示 “ 西安 鹏润 置业 有限公司 由鹏润 控股 有限公司 出资 设立 ” 。   安鹏润 置业 有限公司 、 鹏润 控股 有限公司 法人代表 均 为 黄秀虹 （ 原 国美 集团 总裁 黄光裕 之妹 。 这 两家 公司 与 国美 在线 金融 都 存在 着 关联 关系 。 以此 来看 ， 国美 在线 金融 可能 涉嫌 关联 企业 融资 和 关联 企业 担保 。 据 去年 8 月 24 日 发布 的 《 网络 借贷 信息 中介机构 业务 活动 管理 暂行办法 》 （ 下文 简称 暂行办法 ） 规定 ， 网贷 平台 不得 为 自身 或 变相 为 自身 融资 。 类似 上 文中说 的 这种 关联 企业 融资 的 这种 行为 并 没有 相关 规定 。   中国政法大学 教授 李爱君 认为 ， “ 为 自身 或 变相 为 自身 融资 ” 一定 要 注意 “ 变相 ” 二字 ， 这是 体现 “ 实质 ” 行为 ， 不能 通过 结构设计 进行 变相 自身 融资 。 这 对 目前 一些 机构 影响 较大 ， 实践 中 部分 平台 通过 一些 结构设计 实质 上 主要 是 为 自身 或 关联 公司 进行 融资 的 行为 是 被 禁止 的 。   为信 企业 贷款   据 网贷 天眼 ， 据 投资人 爆料 ， 在 国美 旗下 P2P 平台 美美 理财 上 ， 多个 项目 的 借款 企业 有 失信 被 执行 记录 ， 或是 涉及 多 起 合同纠纷 等 情况 。 用 投资人 的话 说 ， “ 借款 企业 的 底子 不太 干净 ";
     
        //这一步 ，其实是要用分词 工具的，抓取的文本 ，应该不是分完词的
      
       
        
        
        String line = "小米 雷军 ， 乐视 贾跃亭 ， 是 中国 为数不多 富有 创新 与 冒险 精神 的 企业家 。 一直 以来 ， 雷军 提出 小米 生态 链 与 贾跃亭 乐视 生态化 反 ， 是 传统 制造业 与 互联网 融合 模式 探索 。 在 早期 ， 两者 都 取得 了 巨大 的 成功 ， 获得 了 媒体 的 关注 和 赞誉 ； 但 现在 ， 无论 小米 与 乐视 ， 总是 被 各种 负面 报道 伴随 着 ， 不仅 让 我们 思考 ： 这 两家 都 宣称 做 生态 的 企业 ， 究竟 谁 会 胜出 ， 差距 在 哪里 呢 ？ 通过 窥探 两者 股权 架构 的 设计 ， 我们 或许 可以 看出 一丝 端倪 。 先看 雷军 的 小米 的 股权 架构 ， 很 有 参考 雷军 小米 是 海外 架构 ， 通过 查询 国内 工商登记 情况 ， 可 在 一定 程度 上 了解 这 两个 生态 的 股权 架构 情况 。 下图 是 小米 集团 的 股权 架构 ， 顶层 是 雷军 、 黎万强 、 洪峰 、 刘德 四位 创始人 。 在 工商登记 ， 分别 持股 77";
        String[] test= line.split(" ");
        System.out.println("dddd:"+test[0]);
        fasttext.loadModel("E://fasttext_model//news_fasttext.model.bin");  
        List<Pair<Float, String>> list = fasttext.predict(test, 2);  //得到最大可能的六个预测概率  
        
        for (Pair<Float, String> parir : list) 
        {  
        	   System.out.println( parir.getValue());  
        	   System.out.println( parir.toString());  
               System.out.println("key is:" + Math.exp(parir.getKey()) + "   value is:"  + parir.getValue());  
         }  
     System.out.println(Math.exp(list.get(0).getKey()));  //得到最大预测概率  
     System.out.println((list.get(0).getValue()));  //得到最大预测概率  

     System.out.println(Math.exp(list.get(1).getKey()));  //得到最大预测概率  
     System.out.println((list.get(1).getValue()));  //得到最大预测概率  
 
    
    }  

}