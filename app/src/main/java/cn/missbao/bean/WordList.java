package cn.missbao.bean;

/**
 * Role
 * Created by 冀泽阳 on 2016/5/6.
 */
public class WordList {
    public String[] word;
    public String[] soundmark;
    public String[] translate;

    public WordList() {
        word = new String[]{"abandon","abstract","accordingly","adequate","afterward",
                "altitude","amuse","analysis","authority","auxiliary",
                "awkward","cɔin","collective","combination","companion",
                "encourage","encounter","enclose","enable","empty",
                "employment","employer","employee","employ","empire",
                "emphasize","emphasis","emperor","emotional","emotion",
                "emit","day","dawn","daughter","date",
                "data","dash","darling","dark","daring",
                "dare","dangerous","danger","dance","damp",
                "damage","costly","cost","corridor","corresponding",
                "correspond","correct","corporation","corner","corn",
                "fifteen","fierce","field","fiction","fibre",
                "few","fever","fetch","festival","fertilizer",
                "genius","gentle","gentleman","genuine","geography",
                "geometry","germ","German","gesture","fertile",
                "hunt","generally","generate","generation","generator",
                "generous","human","humble","humorous","humour",
                "hundred","hunger","hungry","interference","interior",
                "intermediate","internal","international","interpret","interpretation",
                "justice","justify","interest","interesting","interfere",};
        soundmark = new String[]{"[ə’bændə]","[’æbstrækt]","[ə’kɔr:diŋli]","[’ædikwit]","[‘a:ftəwəd(z)",
                "[‘æltitju:d]","[ə’mju:z]","[ə’næləsis]","[ɔ:’θɔriti]","[ɔ:g’ziljəri]",
                "[‘ɔ:kwəd]","[kɔin]","[kə’lektiv]","[kɔmbi’neiʃən]","[kəm’pæniən]",
                "[in’kΛridʒ]","[in’kauntə]","[in’kləuz]","[i’neibl]","[‘empti]",
                "[im’plɔimənt]","[im’plɔiə]","[emplɔi’i:]","[im’plɔi]","[‘empaiə]",
                "[‘emfəsaiz]","[‘emfəsis]","[‘empərə]","[i’məuʃənl]","[i’məuʃən]",
                "[i’mit]","[dei]","[dɔ:n]","[‘dɔ:tə]","[deit]",
                "[‘deitə]","[dæʃ]","[‘dɑ:liŋ]","[dɑ:k]","[‘deərɪŋ]",
                "[dɛə]","[‘deindʒərəs]","[‘deindʒə]","[dɑ:ns]","[dæmp]",
                "[‘dæmidʒ]","[‘kɔstli]","[kɔst]","[‘kɔridɔ:]","[kɔris’pɔndiŋ]",
                "[kɔris’pɔnd]","[kə’rekt]","[kɔ:pə’reiʃən]","[‘kɔ:nə]","[kɔ:n]",
                "[‘fif’ti:n]","[fiəs]","[fi:ld]","[‘fikʃən]","[‘faibə]",
                "[fju:]","[‘fi:və]","[fetʃ]","[‘festəvəl]","[‘fə:tilaizə]",
                "[‘dʒi:njəs]","[‘dʒentl]","[‘dʒentlmən]","[‘dʒenjuin]","[dʒi’ɔgrəfi]",
                "[dʒi’ɔmitri]","[dʒə:m]","[‘dʒə:mən]","[‘dʒestʃə]","[‘fə:tail]",
                "[hΛnt]","[‘dʒenərəli]","[‘dʒenəreit]","[dʒenə’reiʃən]","[‘dʒenəreitə]",
                "[‘dʒenərəs]","[‘hju:mən]","[‘hΛmbl]","[‘hju:mərəs]","[‘hju:mə]",
                "[‘hΛndrid]","[‘hΛŋgə]","[‘hΛŋgri]","[intə’fiərəns]","[in’tiəriə]",
                "[intə’mi:djət]","[in’tə:nl]","[intə(:)’næʃənl]","[in’tə:prit]","[intə:pri’teiʃən]",
                "[‘dʒΛstis]","[‘dʒΛstifai]","[‘intrist]","[‘intristiŋ]","[intə’fiə]",};
        translate = new String[]{"丢弃；放弃，抛弃","摘要","因此，所以；照着","足够的；可以胜任的","后来，以后",
                "高，高度；高处","逗…乐；给…娱乐","分析，分解，解析","当局，官方；权力","辅助的；附属的",
                "笨拙的；尴尬的","硬币；铸造(硬币)","集体的；集合性的",".结合，联合；化合","同伴；共事者；伴侣",
                "鼓励，支持，助长","遭遇，遇到,遭遇","围住，圈起；附上","使能够，使可能","空的；空洞的",
                "工业；雇用；使用","雇佣者，雇主","受雇者，雇员，雇工","雇用；用；使忙于","帝国",
                "强调，着重","强调，重点，重要性","皇帝","感情的，情绪的","情感，感情；激动",
                "散发；发射；发表","(一)天,白昼,白天","黎明;开端,破晓","女儿","日期;注…日期",
                "数据; 资料","使猛撞;溅,猛冲","亲爱的人;宠儿","暗的;黑色的","大胆的,勇敢的",
                "敢;竟敢","危险的,不安全的","危险;危险事物","跳舞;摇晃;舞","潮湿的,有湿气的",
                "损害,毁坏;损害","昂贵的；价值高的","价格，代价；成本","走廊，回廊，通路","相应的；符合的",
                "相符合；相当","正确的,纠正","公司，企业；社团","角；犄角；边远地区","谷物；(英)小麦",
                "十五；十五个","凶猛的，狂热的","田野；田；运动场","小说；虚构，杜撰","纤维，纤维质",
                "很少的；少数的","发热，发烧；狂热","拿来；请来，接去","节日；音乐节","肥料",
                "天才，天赋，天资","和蔼的；轻柔的","绅士；有教养的人","真的；真正的","地理，地理学",
                "几何，几何学","微生物，细菌，幼芽","德国的n.德国人","姿势，手势；姿态","肥沃的；多产的",
                "打猎；搜寻vt.追猎","一般地；通常地","发生；引起；生殖","一代，一代人；产生","发电机；发生者",
                "慷慨的；宽厚的","人的，人类的n.人","谦逊的；地位低下的","富于幽默的，诙谐的","幽默，诙谐，幽默感",
                "百，百个n.许多","饿，饥饿；渴望","饥饿的；渴望的","干涉，干预；阻碍","内的；内地的n.内部",
                "中间的；中级的","内的；国内的","国际的，世界(性)的","解释，说明；口译","解释；口译",
                "正义，公正；司法","证明…是正当的","兴趣；利益；利息","有趣的，引人入胜的","干涉，干预；妨碍",};
    }


//    lately/ ‘leitli/ ad.最近，不久前
//    later/ ‘leitə/ ad.后来；过一会儿
//    Latin/ ‘lætin/ a.拉丁的n.拉丁语
//    latter/ ‘lætə/ a.(两者中)后者的
//    laugh/ la:f/ vi.笑，发笑n.笑
//    laughter/ la:ftə/ n.笑，笑声
//    launch/ lɔ:ntʃ/ vt.发射，投射；发动
//    laundry/ ‘lɔ:ndri/ n.洗衣房，洗衣店
//    lavatory/ ‘lævətəri/ n.盥洗室，厕所
//    kneel/ ni:l/ vi.跪，跪下，跪着
//    knife/ naif/ n.小刀，刀，餐刀
//    knock/ nɔk/ vi.&vt.&n.敲，击，打
//    knot/ nɔt/ n.(绳的)结，(树的)节
//    know/ nəu/ vt.知道；认识；通晓
//    knowledge/ ‘nɔlidʒ/ n.知识，学识；知道
//    judgement/ ‘dʒΛdʒmənt/ n.意见；审判；判断
//    juice/ dʒu:s/ n.(水果等)汁，液
//    July/ dʒu:’lai/ n.七月
//    jump/ dʒΛmp/ vi.跳；暴涨vt.跳过
//    June/ dʒu:n/ n.六月
//    jungle/ ‘dʒΛŋgl/ n.丛林，密林，莽丛
//    junior/ ‘dʒu:njə/ a.年少的n.晚辈
//    jury/ ‘dʒuəri/ n.陪审团；评奖团
//    just/ dʒΛst/ ad.刚才；只是；正好

}
