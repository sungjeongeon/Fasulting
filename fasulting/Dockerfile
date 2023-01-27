FROM node:18.12.1 as build-stage
# WORKDIR $PWD/front_end
# COPY package*.json ./
COPY . .
RUN npm install --legacy-peer-deps
# COPY . .
RUN npm run build
RUN ls

FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /var/jenkins_home/workspace/deploytest/testproject_react/build /usr/share/nginx/html
#COPY --from=build-stage /var/jenkins_home/workspace/deploytest/testproject_react/deploy_conf/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]