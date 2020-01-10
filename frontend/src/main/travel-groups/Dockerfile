# we choose the lts version
FROM node:lts
# set working directory
WORKDIR /app
# copy and install app dependencies
COPY package.json /app/package.json
RUN npm install
RUN npm install -g @angular/cli@latest
# add app
COPY . /app
EXPOSE 4200
# start app
CMD ["ng",  "serve", "--host", "0.0.0.0"]
