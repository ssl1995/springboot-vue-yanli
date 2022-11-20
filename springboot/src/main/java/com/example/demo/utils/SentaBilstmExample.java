package com.example.demo.utils;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
public class SentaBilstmExample {
    private static final Logger logger = LoggerFactory.getLogger(SentaBilstmExample.class);

    private SentaBilstmExample() {}

    public static void main(String[] args) throws IOException, TranslateException, ModelException {

        // 分词
        Lac lac = new Lac();
        Criteria<String, String[][]> lacCriteria = lac.criteria();
        // 情感分析
        SentaBilstm senta = new SentaBilstm();
        Criteria<String[], float[]> SentaCriteria = senta.criteria();

        try (ZooModel<String, String[][]> lacModel = lacCriteria.loadModel();
             Predictor<String, String[][]> lacPredictor = lacModel.newPredictor();
             ZooModel<String[], float[]> sentaModel = SentaCriteria.loadModel();
             Predictor<String[], float[]> sentaPredictor = sentaModel.newPredictor()) {

            String input = "这家餐厅很好吃";
            logger.info("输入句子: {}", input);

            String[][] lacResult = lacPredictor.predict(input);
            // 分词
            logger.info("Words : " + Arrays.toString(lacResult[0]));
            logger.info("Tags : " + Arrays.toString(lacResult[1]));

            // 情感分析
            float[] sentaResult = sentaPredictor.predict(lacResult[0]);
            logger.info(Arrays.toString(sentaResult));
            logger.info("negative : " + sentaResult[0]);
            logger.info("positive : " + sentaResult[1]);

        }
    }
}
