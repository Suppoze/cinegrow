rs.initiate(
	{
		_id: "shard",
		members: [
			{ _id : 0, host : "127.0.0.1:27011" }
		]
	}
)