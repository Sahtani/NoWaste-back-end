/*
package com.youcode.nowastebackend.service;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

import java.sql.Array;

public class ImageRecognition {
    public static void main(String[] args) {
        try (Graph graph = new Graph()) {
            // Charger le modèle pré-entraîné
            byte[] graphDef = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("chemin/vers/modele.pb"));
            graph.importGraphDef(graphDef);

            // Créer une session pour exécuter le modèle
            try (Session session = new Session(graph)) {
                // Préparer l'image d'entrée (doit être prétraitée selon les besoins du modèle)
                float[] imageData = new Array[]; // Charger et prétraiter l'image
                Tensor<?> inputTensor = Tensor.create(imageData);

                // Exécuter le modèle
                Tensor<?> result = session.runner()
                        .feed("input_tensor_name", inputTensor)
                        .fetch("output_tensor_name")
                        .run()
                        .get(0);

                // Traiter les résultats
                float[] probabilities = new float[(int) result.shape()[1]];
                result.copyTo(probabilities);

                // Afficher les résultats
                for (float prob : probabilities) {
                    System.out.println(prob);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
