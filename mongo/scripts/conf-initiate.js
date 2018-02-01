rs.initiate(
	{
		_id: "conf",
		configsvr: true,
		members: [
			{ _id : 0, host : "127.0.0.1:27001" }
		]
	}
)