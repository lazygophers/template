package lmq

import (
	"github.com/lazygophers/lrpc"
	"github.com/lazygophers/lrpc/middleware/service_discovery/ldiscovery"
)

func init() {
	lrpc.DiscoveryClient = ldiscovery.DiscoveryClient
}
