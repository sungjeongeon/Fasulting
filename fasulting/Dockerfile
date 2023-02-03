FROM node:18.12.1 as build-stage
# WORKDIR $PWD/front_end
# COPY package*.json ./
COPY . .
RUN npm install
# COPY . .
RUN npm run build
RUN ls

# runner
#FROM nginx:stable-alpine as production-stage
#COPY --from=build-stage /var/jenkins_home/workspace/deploytest/testproject_react/build /usr/share/nginx/html
#COPY --from=build-stage /var/jenkins_home/workspace/deploytest/testproject_react/deploy_conf/nginx.conf /etc/nginx/conf.d/default.conf
# 3000번 포트 노출
EXPOSE 3000

# npm start 스크립트 실행
CMD ["npm", "start"]
