#### Stage 1: Build the angular application
FROM node as build
 
# Configure the main working directory inside the docker image. 
# This is the base directory used in any further RUN, COPY, and ENTRYPOINT 
# commands.
WORKDIR /app
 
# Copy the package.json as well as the package-lock.json and install 
# the dependencies. This is a separate step so the dependencies 
# will be cached unless changes to one of those two files 
# are made.
COPY package*.json ./
RUN npm ci
 
# Copy the main application
COPY . ./
 
# Build the application
RUN npm run build --prod
 
#### Stage 2, use the compiled app, ready for production with Nginx
FROM nginx:1.15.8-alpine

#Copy nginx conf file to NGINX folder
COPY --from=build /app/nginx.conf /etc/nginx/nginx.conf
 
#Copy built angular app files to NGINX HTML folder
COPY --from=build /app/dist/youdo/ /usr/share/nginx/html

CMD ["/bin/sh", "-c", \
"echo API_URL=[$API_URL], && \
sed -i s#API_URL#$API_URL#g /usr/share/nginx/html/main.*.js && \
nginx -g 'daemon off;'"]