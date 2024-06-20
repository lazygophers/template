package main

import (
	"{{ .PB.GoPackage }}/internal/state"
	"github.com/lazygophers/log"
	"github.com/lazygophers/lrpc/middleware/service_discovery/ldiscovery"
)

func main() {
	err := state.Load()
	if err != nil {
		log.Errorf("err:%v", err)
		return
	}

	app.OnListen(ldiscovery.OnListen)
	app.OnShutdown(ldiscovery.OnShutdown)

	app.AddRoutes(Routes)

	err = app.ListenAndServe(state.State.Config.Port)
	if err != nil {
		log.Errorf("err:%v", err)
		return
	}
}
