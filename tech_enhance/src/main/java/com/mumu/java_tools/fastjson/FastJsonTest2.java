package com.mumu.java_tools.fastjson;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * FastJsonTest2
 *
 * @author liuzhen
 * @version 1.0.0 2023/2/24 14:51
 */
public class FastJsonTest2 {

    /**
     * 要想解析这种复杂的字符串，把它转换成java类的话，首先得先定义好与之相符的java POJO 对象，从上面的json字符串组成来看，我们可以拆分出来四个bean：
     *
     * 最外层的响应：ResponseData
     * 返回结果：ResultBean
     * 数据：DataBean
     * 做菜步骤：StepsBean
     *
     */


    /**
     * 将复杂的json串转换为java类
     */
    @Test
    public void jsonToComplexObj() {
        // 读取类路径下的caipu.json文件,这里使用了第三方工具hutool进行读取json文件
        // 工具类参见：https://hutool.cn/docs/#/
        String path = "src\\test\\java\\com\\gobestsoft\\java_tools\\fastjson\\caipu.json";
        String jsonStr = FileUtil.readUtf8String(new File(path));
        System.out.println(jsonStr);
        // 转换为java类
        ResponseData resp = JSON.parseObject(jsonStr, ResponseData.class);
        System.out.println(resp);

        // 通过对象操作数据
        // 获取响应码resultcode
        System.out.println(resp.getResultcode());
        // 获取响应数据
        ResponseData.ResultBean result = resp.getResult();
        System.out.println("result响应数据：" + result);
    }


}

class ResponseData {

    /**
     * resultcode : 200
     * reason : Success
     * result : {"data":[{"id":"6269","title":"羊肉汤","tags":"增强抵抗力;煮;家常菜;汤;鲁菜","imtro":"邹城人有喝羊汤的习惯，春夏秋冬羊汤馆总断不了食客，春秋天气候干燥要喝，夏天入伏要喝\u201c伏羊汤\u201d，阴冷的冬季尤其要喝碗羊汤才够温暖。以至于邀友喝羊汤成为了礼仪；\u201c二哥，晚上咱们喝羊汤去\u201d。邹城的羊汤铺遍地开花，以至于单县羊汤、滕州羊汤在邹城都没有了用武之地。我们这里的羊汤做法是最纯的，基本不放煮肉的香料，就用羊骨和羊肉煮成，\u201c肉嫩汤浓\u201d是其特色。 煮羊汤要先煮羊骨，把羊骨斩成大段焯水后放一点羊板油用细火煮，煮到汤白味浓时放入羊肉。羊肉煮到用筷子能轻松插穿时就要捞出，久煮的话羊肉过烂就失去了软嫩的口感。 碗里放入葱花或蒜粒，调入精盐，放入切的薄薄的羊肉片。把烧的滚开的羊汤盛到碗里，洒上香菜，再挖上一匙子香辣的用羊油泼成的辣椒油，一个字\u201c香\u201d！","ingredients":"山羊肉,500g;羊骨,1000g","burden":"生姜,适量;精盐,适量;香菜,适量;大葱,适量;辣椒油,适量;羊板油,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/7/6269_379835.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_95d65e77b58a1b6b.jpg","step":"1.羊脊骨洗净用刀斩成段。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_a8136c10401a1643.jpg","step":"2.煮锅里倒入清水，放入羊脊骨，羊肉煮开后捞出。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_c7b1c9fc85ddc6de.jpg","step":"3.煮锅里倒入开水，放入羊脊骨生姜块大火煮开后改小火。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_2b284dc30b4f0875.jpg","step":"4.小火煮40分钟，煮至汤色发白。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_c7ade6439eb2db5a.jpg","step":"5.放入羊肉，加入适量的羊板油小火煮30分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_579748e3b0f15963.jpg","step":"6.捞出煮好的羊肉，晾凉后切薄片。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_1550e6f127aa1077.jpg","step":"7.碗里放入葱花，调入精盐。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_a2c965d77b96da70.jpg","step":"8.放入羊肉片，把滚开的羊汤倒入碗里洒上香菜末。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_eea9b807d1dc5995.jpg","step":"9.可以根据喜好调入陈醋放入蒜粒，最后调入辣椒油即可。"}]}],"totalNum":"9","pn":0,"rn":"1"}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * data : [{"id":"6269","title":"羊肉汤","tags":"增强抵抗力;煮;家常菜;汤;鲁菜","imtro":"邹城人有喝羊汤的习惯，春夏秋冬羊汤馆总断不了食客，春秋天气候干燥要喝，夏天入伏要喝\u201c伏羊汤\u201d，阴冷的冬季尤其要喝碗羊汤才够温暖。以至于邀友喝羊汤成为了礼仪；\u201c二哥，晚上咱们喝羊汤去\u201d。邹城的羊汤铺遍地开花，以至于单县羊汤、滕州羊汤在邹城都没有了用武之地。我们这里的羊汤做法是最纯的，基本不放煮肉的香料，就用羊骨和羊肉煮成，\u201c肉嫩汤浓\u201d是其特色。 煮羊汤要先煮羊骨，把羊骨斩成大段焯水后放一点羊板油用细火煮，煮到汤白味浓时放入羊肉。羊肉煮到用筷子能轻松插穿时就要捞出，久煮的话羊肉过烂就失去了软嫩的口感。 碗里放入葱花或蒜粒，调入精盐，放入切的薄薄的羊肉片。把烧的滚开的羊汤盛到碗里，洒上香菜，再挖上一匙子香辣的用羊油泼成的辣椒油，一个字\u201c香\u201d！","ingredients":"山羊肉,500g;羊骨,1000g","burden":"生姜,适量;精盐,适量;香菜,适量;大葱,适量;辣椒油,适量;羊板油,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/7/6269_379835.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_95d65e77b58a1b6b.jpg","step":"1.羊脊骨洗净用刀斩成段。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_a8136c10401a1643.jpg","step":"2.煮锅里倒入清水，放入羊脊骨，羊肉煮开后捞出。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_c7b1c9fc85ddc6de.jpg","step":"3.煮锅里倒入开水，放入羊脊骨生姜块大火煮开后改小火。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_2b284dc30b4f0875.jpg","step":"4.小火煮40分钟，煮至汤色发白。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_c7ade6439eb2db5a.jpg","step":"5.放入羊肉，加入适量的羊板油小火煮30分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_579748e3b0f15963.jpg","step":"6.捞出煮好的羊肉，晾凉后切薄片。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_1550e6f127aa1077.jpg","step":"7.碗里放入葱花，调入精盐。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_a2c965d77b96da70.jpg","step":"8.放入羊肉片，把滚开的羊汤倒入碗里洒上香菜末。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_eea9b807d1dc5995.jpg","step":"9.可以根据喜好调入陈醋放入蒜粒，最后调入辣椒油即可。"}]}]
         * totalNum : 9
         * pn : 0
         * rn : 1
         */

        private String totalNum;
        private int pn;
        private String rn;
        private List<DataBean> data;

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public int getPn() {
            return pn;
        }

        public void setPn(int pn) {
            this.pn = pn;
        }

        public String getRn() {
            return rn;
        }

        public void setRn(String rn) {
            this.rn = rn;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 6269
             * title : 羊肉汤
             * tags : 增强抵抗力;煮;家常菜;汤;鲁菜
             * imtro : 邹城人有喝羊汤的习惯，春夏秋冬羊汤馆总断不了食客，春秋天气候干燥要喝，夏天入伏要喝“伏羊汤”，阴冷的冬季尤其要喝碗羊汤才够温暖。以至于邀友喝羊汤成为了礼仪；“二哥，晚上咱们喝羊汤去”。邹城的羊汤铺遍地开花，以至于单县羊汤、滕州羊汤在邹城都没有了用武之地。我们这里的羊汤做法是最纯的，基本不放煮肉的香料，就用羊骨和羊肉煮成，“肉嫩汤浓”是其特色。 煮羊汤要先煮羊骨，把羊骨斩成大段焯水后放一点羊板油用细火煮，煮到汤白味浓时放入羊肉。羊肉煮到用筷子能轻松插穿时就要捞出，久煮的话羊肉过烂就失去了软嫩的口感。 碗里放入葱花或蒜粒，调入精盐，放入切的薄薄的羊肉片。把烧的滚开的羊汤盛到碗里，洒上香菜，再挖上一匙子香辣的用羊油泼成的辣椒油，一个字“香”！
             * ingredients : 山羊肉,500g;羊骨,1000g
             * burden : 生姜,适量;精盐,适量;香菜,适量;大葱,适量;辣椒油,适量;羊板油,适量
             * albums : ["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/7/6269_379835.jpg"]
             * steps : [{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_95d65e77b58a1b6b.jpg","step":"1.羊脊骨洗净用刀斩成段。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_a8136c10401a1643.jpg","step":"2.煮锅里倒入清水，放入羊脊骨，羊肉煮开后捞出。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_c7b1c9fc85ddc6de.jpg","step":"3.煮锅里倒入开水，放入羊脊骨生姜块大火煮开后改小火。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_2b284dc30b4f0875.jpg","step":"4.小火煮40分钟，煮至汤色发白。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_c7ade6439eb2db5a.jpg","step":"5.放入羊肉，加入适量的羊板油小火煮30分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_579748e3b0f15963.jpg","step":"6.捞出煮好的羊肉，晾凉后切薄片。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_1550e6f127aa1077.jpg","step":"7.碗里放入葱花，调入精盐。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_a2c965d77b96da70.jpg","step":"8.放入羊肉片，把滚开的羊汤倒入碗里洒上香菜末。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_eea9b807d1dc5995.jpg","step":"9.可以根据喜好调入陈醋放入蒜粒，最后调入辣椒油即可。"}]
             */

            private String id;
            private String title;
            private String tags;
            private String imtro;
            private String ingredients;
            private String burden;
            private List<String> albums;
            private List<StepsBean> steps;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getImtro() {
                return imtro;
            }

            public void setImtro(String imtro) {
                this.imtro = imtro;
            }

            public String getIngredients() {
                return ingredients;
            }

            public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
            }

            public String getBurden() {
                return burden;
            }

            public void setBurden(String burden) {
                this.burden = burden;
            }

            public List<String> getAlbums() {
                return albums;
            }

            public void setAlbums(List<String> albums) {
                this.albums = albums;
            }

            public List<StepsBean> getSteps() {
                return steps;
            }

            public void setSteps(List<StepsBean> steps) {
                this.steps = steps;
            }

            public static class StepsBean {
                /**
                 * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/63/6269_95d65e77b58a1b6b.jpg
                 * step : 1.羊脊骨洗净用刀斩成段。
                 */

                private String img;
                private String step;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getStep() {
                    return step;
                }

                public void setStep(String step) {
                    this.step = step;
                }
            }
        }
    }
}


