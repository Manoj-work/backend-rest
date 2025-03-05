pipeline {
    agent any

    environment {
        PODMAN_CMD = "/usr/bin/podman"  // Path to Podman on your server
        IMAGE_NAME = "backend"
        IMAGE_TAG = "latest"
        TAR_FILE = "backend.tar"
        SSH_USER = "ankitm"
        SHARED_DIR = "/home/ankitm/shared"  // Path to the shared directory on your server
        GIT_REPO = "http://192.168.0.101:3000/jadhav.manoj/Backend-Rest.git"
        GIT_BRANCH = "main"
        GIT_CREDENTIALS_ID = "Jenkins"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git credentialsId: "${GIT_CREDENTIALS_ID}",  branch: 'main', url: 'http://192.168.0.101:3000/jadhav.manoj/Backend-Rest.git'
            }
        }

        stage('Build JAR File with Gradle') {
            steps {
                script {
                    try {
                        sh './gradlew clean build'
                        echo "✅ Gradle build completed successfully."
                    } catch (Exception e) {
                        error "❌ Gradle build failed: ${e.message}"
                    }
                }
            }
        }

        stage('Build and Save Podman Image') {
            steps {
                script {
                    try {
                        // Use Podman instead of Docker to build and save the image
                        sh "${PODMAN_CMD} build --platform=linux/amd64 -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                        sh "${PODMAN_CMD} save -o ${TAR_FILE} ${IMAGE_NAME}:${IMAGE_TAG}"
                        echo "✅ Podman Image Built and Saved: ${IMAGE_NAME}:${IMAGE_TAG}"
                    } catch (Exception e) {
                        error "❌ Podman build or save failed: ${e.message}"
                    }
                }
            }
        }

        stage('Move TAR File to Shared Repository') {
            steps {
                script {
                    try {
                        // Move the tar file to the shared directory on the server
                        sh "mv ${TAR_FILE} ${SHARED_DIR}/"
                        echo "✅ TAR file moved to shared repository."
                    } catch (Exception e) {
                        error "❌ Failed to move TAR file to shared repository: ${e.message}"
                    }
                }
            }
        }

        stage('Deploy with Podman on Server') {
            steps {
                script {
                    try {
                        // Since Jenkins is on the same server, we can run the commands directly
                        sh """
                            set -xe;
                            sudo -u podman -i podman stop backend || true;
                            sudo -u podman -i podman rm backend || true;
                            sudo -u podman -i podman load -i ${SHARED_DIR}/${TAR_FILE};
                            sudo -u podman -i podman run -d --name backend --network shared -p 4000:4000 backend:latest
                            echo '✅ Backend container started successfully.'
                        """
                        echo "✅ Deployment with Podman completed."
                    } catch (Exception e) {
                        error "❌ Deployment failed: ${e.message}"
                    }
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                script {
                    try {
                        // SSH into the server and check Podman container status
                        sh """
                            set -xe;
                            sudo -u podman -i podman ps -a | grep backend;
                            sudo -u podman -i podman logs backend;
                        """
                        echo "✅ Deployment verification completed."
                    } catch (Exception e) {
                        error "❌ Deployment verification failed: ${e.message}"
                    }
                }
            }
        }
    }
}