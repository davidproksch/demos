var express = require('express'),
    request  = require('request'),
    app     = express();


app.engine('html', require('ejs').renderFile);

var port = process.env.PORT || process.env.OPENSHIFT_NODEJS_PORT || 8080,
    ip   = process.env.IP   || process.env.OPENSHIFT_NODEJS_IP || '0.0.0.0',
    wapi = process.env.weather_api_key || process.env.WEATHER_API_KEY ;

var getWeather = function(zip, wapi,res) {
	var myURL = 'http://api.openweathermap.org/data/2.5/weather?zip='+zip+',us&appid='+wapi;
	request(myURL, function (error, response, body) {
		res.send(body);
	});
};

app.get('/weather', function(req,res) {
	//res.render('index.html');
	var zip = req.query.zip;
	
	//res.send(getWeather(zip,wapi));
	getWeather(zip,wapi,res);
	//res.send('Hello World!');
});

app.listen(port,ip);
console.log('Server running on http://%s:%s', ip, port);
